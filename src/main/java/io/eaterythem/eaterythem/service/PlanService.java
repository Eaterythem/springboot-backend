package io.eaterythem.eaterythem.service;

import io.eaterythem.eaterythem.dto.PlanDTO;
import io.eaterythem.eaterythem.exception.BadRequestException;
import io.eaterythem.eaterythem.exception.UnauthorizedException;
import io.eaterythem.eaterythem.mapper.PlanMapper;
import io.eaterythem.eaterythem.model.Cycle;
import io.eaterythem.eaterythem.model.Entry;
import io.eaterythem.eaterythem.model.Plan;
import io.eaterythem.eaterythem.model.PlanParticipant;
import io.eaterythem.eaterythem.model.User;
import io.eaterythem.eaterythem.model.enums.ParticipantRole;
import io.eaterythem.eaterythem.repository.PlanParticipantRepository;
import io.eaterythem.eaterythem.repository.PlanRepository;
import io.eaterythem.eaterythem.tools.ObjectMerger;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import java.util.*;

@AllArgsConstructor
@Service
public class PlanService {
    EntryService entryService;

    PlanRepository planRepository;
    PlanParticipantRepository planParticipantRepository;

    PlanMapper planMapper;

    public List<PlanDTO> getAllPlans() {
        List<Plan> plans = planRepository.findAll();
        List<PlanDTO> planDTOs = planMapper.toDTO(plans);
        return planDTOs;
    }

    public PlanDTO getPlanById(Integer id) {
        Plan plan = planRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Plan not found"));

        return planMapper.toDTO(plan);
    }

    public List<PlanDTO> getMePlans(Integer userId) {
        List<Plan> plans = planRepository.getByUserId(userId);
        List<PlanDTO> planDTOs = planMapper.toDTO(plans);
        return planDTOs;
    }

    public PlanDTO createPlan(PlanDTO planDTO, Integer userId) {
        Plan plan = planMapper.toEntity(planDTO);

        Cycle cycle;
        if ((cycle = plan.getBreakfastCycle()) != null) {
            entryService.createEntry(
                    entryService.entryMapper.toDTO(
                            Entry.builder()
                                    .plannedRecipe(cycle.getRecipes().get(0).getRecipe())
                                    .plan(plan)
                                    .build()));
        }
        if ((cycle = plan.getDinnerCycle()) != null) {
            entryService.createEntry(
                    entryService.entryMapper.toDTO(
                            Entry.builder()
                                    .plannedRecipe(cycle.getRecipes().get(0).getRecipe())
                                    .plan(plan)
                                    .build()));
        }
        if ((cycle = plan.getLunchCycle()) != null) {
            entryService.createEntry(
                    entryService.entryMapper.toDTO(
                            Entry.builder()
                                    .plannedRecipe(cycle.getRecipes().get(0).getRecipe())
                                    .plan(plan)
                                    .build()));
        }

        User user = new User();
        user.setId(userId);

        List<PlanParticipant> pp = new ArrayList<PlanParticipant>();
        pp.add(PlanParticipant.builder()
                .user(user)
                .plan(plan)
                .role(ParticipantRole.OWNER)
                .build());
        plan.setParticipants(pp);

        return planMapper.toDTO(planRepository.save(plan));
    }

    public PlanDTO updatePlan(Integer id, PlanDTO planDTO, Integer userId) {
        Plan plan = planRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Plan not found"));

        if (planParticipantRepository.getUserRole(userId, plan.getId()) != ParticipantRole.OWNER) {
            throw new UnauthorizedException("Only Plan Owner can edit");
        }

        planDTO.setEntries(null);

        plan = ObjectMerger.mergeNonNullFields(plan, planMapper.toEntity(planDTO));

        return planMapper.toDTO(planRepository.save(plan));
    }

    public void deletePlan(Integer id, Integer userId) {
        Plan plan = planRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Plan not found"));

        if (planParticipantRepository.getUserRole(userId, plan.getId()) != ParticipantRole.OWNER) {
            throw new UnauthorizedException("Only Plan Owner can delete");
        }

        planRepository.deleteById(id);
    }
}