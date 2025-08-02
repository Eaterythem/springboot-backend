package io.eaterythem.eaterythem.service;

import io.eaterythem.eaterythem.dto.MealPlanDTO;
import io.eaterythem.eaterythem.exception.BadRequestException;
import io.eaterythem.eaterythem.exception.UnauthorizedException;
import io.eaterythem.eaterythem.mapper.MealPlanMapper;
import io.eaterythem.eaterythem.model.MealPlan;
import io.eaterythem.eaterythem.model.User;
import io.eaterythem.eaterythem.repository.MealPlanRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import java.util.*;

@AllArgsConstructor
@Service
public class MealPlanService {
    MealPlanRepository mealPlanRepository;

    MealPlanMapper mealPlanMapper;

    public List<MealPlanDTO> getAllMealPlans() {
        List<MealPlan> mealPlans = mealPlanRepository.findAll();
        List<MealPlanDTO> mealPlanDTOs = mealPlanMapper.toDTO(mealPlans);
        return mealPlanDTOs;
    }

    public MealPlanDTO getMealPlanById(UUID id) {
        MealPlan mealPlan = mealPlanRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("MealPlan not found"));

        return mealPlanMapper.toDTO(mealPlan);
    }

    public List<MealPlanDTO> getMePlans(UUID userId){
        List<MealPlan> mealPlans = mealPlanRepository.getByUserId(userId);
        List<MealPlanDTO> mealPlanDTOs = mealPlanMapper.toDTO(mealPlans);
        return mealPlanDTOs;
    }

    public MealPlanDTO createMealPlan(MealPlanDTO mealPlanDTO, UUID userId) {
        MealPlan mealPlan = mealPlanMapper.toEntity(mealPlanDTO);
        User user = new User();
        user.setId(userId);
        mealPlan.setUser(user);

        return mealPlanMapper.toDTO(mealPlanRepository.save(mealPlan));
    }

    public MealPlanDTO updateMealPlan(UUID id, MealPlanDTO mealPlanDTO, UUID userId) {
        MealPlan mealPlan = mealPlanRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("MealPlan not found"));

        if (!mealPlan.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("Only Plan Owner can edit");
        }

        MealPlan newPMealPlan = mealPlanMapper.toEntity(mealPlanDTO);
        newPMealPlan.setId(id);

        return mealPlanMapper.toDTO(mealPlanRepository.save(newPMealPlan));
    }

    public void deleteMealPlan(UUID id, UUID userId) {
        MealPlan mealPlan = mealPlanRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("MealPlan not found"));

        if (!mealPlan.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("Only Plan Owner can delete");
        }

        mealPlanRepository.deleteById(id);
    }
}