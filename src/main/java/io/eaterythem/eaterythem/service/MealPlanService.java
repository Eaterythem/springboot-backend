package io.eaterythem.eaterythem.service;

import io.eaterythem.eaterythem.dto.MealPlanDTO;
import io.eaterythem.eaterythem.exception.BadRequestException;
import io.eaterythem.eaterythem.exception.UnauthorizedException;
import io.eaterythem.eaterythem.mapper.MealPlanMapper;
import io.eaterythem.eaterythem.model.MealCycle;
import io.eaterythem.eaterythem.model.MealEntry;
import io.eaterythem.eaterythem.model.MealPlan;
import io.eaterythem.eaterythem.model.MealPlanParticipant;
import io.eaterythem.eaterythem.model.User;
import io.eaterythem.eaterythem.model.enums.ParticipantRole;
import io.eaterythem.eaterythem.repository.MealPlanParticipantRepository;
import io.eaterythem.eaterythem.repository.MealPlanRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import java.util.*;

@AllArgsConstructor
@Service
public class MealPlanService {
    MealEntryService mealEntryService;

    MealPlanRepository mealPlanRepository;
    MealPlanParticipantRepository mealPlanParticipantRepository;

    MealPlanMapper mealPlanMapper;

    public List<MealPlanDTO> getAllMealPlans() {
        List<MealPlan> mealPlans = mealPlanRepository.findAll();
        List<MealPlanDTO> mealPlanDTOs = mealPlanMapper.toDTO(mealPlans);
        return mealPlanDTOs;
    }

    public MealPlanDTO getMealPlanById(Integer id) {
        MealPlan mealPlan = mealPlanRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("MealPlan not found"));

        return mealPlanMapper.toDTO(mealPlan);
    }

    public List<MealPlanDTO> getMePlans(Integer userId) {
        List<MealPlan> mealPlans = mealPlanRepository.getByUserId(userId);
        List<MealPlanDTO> mealPlanDTOs = mealPlanMapper.toDTO(mealPlans);
        return mealPlanDTOs;
    }

    public MealPlanDTO createMealPlan(MealPlanDTO mealPlanDTO, Integer userId) {
        MealPlan mealPlan = mealPlanMapper.toEntity(mealPlanDTO);

        MealCycle mealCycle;
        if ((mealCycle = mealPlan.getBreakfastCycle()) != null) {
            mealEntryService.createMealEntry(
                    mealEntryService.mealEntryMapper.toDTO(
                            MealEntry.builder()
                                    .plannedRecipe(mealCycle.getRecipes().get(0).getRecipe())
                                    .mealPlan(mealPlan)
                                    .build()));
        }
        if ((mealCycle = mealPlan.getDinnerCycle()) != null) {
            mealEntryService.createMealEntry(
                    mealEntryService.mealEntryMapper.toDTO(
                            MealEntry.builder()
                                    .plannedRecipe(mealCycle.getRecipes().get(0).getRecipe())
                                    .mealPlan(mealPlan)
                                    .build()));
        }
        if ((mealCycle = mealPlan.getLunchCycle()) != null) {
            mealEntryService.createMealEntry(
                    mealEntryService.mealEntryMapper.toDTO(
                            MealEntry.builder()
                                    .plannedRecipe(mealCycle.getRecipes().get(0).getRecipe())
                                    .mealPlan(mealPlan)
                                    .build()));
        }

        User user = new User();
        user.setId(userId);

        List<MealPlanParticipant> pp = new ArrayList<MealPlanParticipant>();
        pp.add(MealPlanParticipant.builder()
                .user(user)
                .mealPlan(mealPlan)
                .role(ParticipantRole.OWNER)
                .build());
        mealPlan.setParticipants(pp);

        return mealPlanMapper.toDTO(mealPlanRepository.save(mealPlan));
    }

    public MealPlanDTO updateMealPlan(Integer id, MealPlanDTO mealPlanDTO, Integer userId) {
        MealPlan mealPlan = mealPlanRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("MealPlan not found"));

        if (mealPlanParticipantRepository.getUserRole(userId, mealPlan.getId()) != ParticipantRole.OWNER) {
            throw new UnauthorizedException("Only Plan Owner can edit");
        }

        MealPlan newPMealPlan = mealPlanMapper.toEntity(mealPlanDTO);
        newPMealPlan.setId(id);

        return mealPlanMapper.toDTO(mealPlanRepository.save(newPMealPlan));
    }

    public void deleteMealPlan(Integer id, Integer userId) {
        MealPlan mealPlan = mealPlanRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("MealPlan not found"));

        if (mealPlanParticipantRepository.getUserRole(userId, mealPlan.getId()) != ParticipantRole.OWNER) {
            throw new UnauthorizedException("Only Plan Owner can delete");
        }

        mealPlanRepository.deleteById(id);
    }
}