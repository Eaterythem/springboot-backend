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
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import java.util.*;

@AllArgsConstructor
@Service
public class PlanService {
    EntryService EntryService;

    PlanRepository PlanRepository;
    PlanParticipantRepository PlanParticipantRepository;

    PlanMapper PlanMapper;

    public List<PlanDTO> getAllPlans() {
        List<Plan> Plans = PlanRepository.findAll();
        List<PlanDTO> PlanDTOs = PlanMapper.toDTO(Plans);
        return PlanDTOs;
    }

    public PlanDTO getPlanById(Integer id) {
        Plan Plan = PlanRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Plan not found"));

        return PlanMapper.toDTO(Plan);
    }

    public List<PlanDTO> getMePlans(Integer userId) {
        List<Plan> Plans = PlanRepository.getByUserId(userId);
        List<PlanDTO> PlanDTOs = PlanMapper.toDTO(Plans);
        return PlanDTOs;
    }

    public PlanDTO createPlan(PlanDTO PlanDTO, Integer userId) {
        Plan Plan = PlanMapper.toEntity(PlanDTO);

        Cycle Cycle;
        if ((Cycle = Plan.getBreakfastCycle()) != null) {
            EntryService.createEntry(
                    EntryService.EntryMapper.toDTO(
                            Entry.builder()
                                    .plannedRecipe(Cycle.getRecipes().get(0).getRecipe())
                                    .Plan(Plan)
                                    .build()));
        }
        if ((Cycle = Plan.getDinnerCycle()) != null) {
            EntryService.createEntry(
                    EntryService.EntryMapper.toDTO(
                            Entry.builder()
                                    .plannedRecipe(Cycle.getRecipes().get(0).getRecipe())
                                    .Plan(Plan)
                                    .build()));
        }
        if ((Cycle = Plan.getLunchCycle()) != null) {
            EntryService.createEntry(
                    EntryService.EntryMapper.toDTO(
                            Entry.builder()
                                    .plannedRecipe(Cycle.getRecipes().get(0).getRecipe())
                                    .Plan(Plan)
                                    .build()));
        }

        User user = new User();
        user.setId(userId);

        List<PlanParticipant> pp = new ArrayList<PlanParticipant>();
        pp.add(PlanParticipant.builder()
                .user(user)
                .Plan(Plan)
                .role(ParticipantRole.OWNER)
                .build());
        Plan.setParticipants(pp);

        return PlanMapper.toDTO(PlanRepository.save(Plan));
    }

    public PlanDTO updatePlan(Integer id, PlanDTO PlanDTO, Integer userId) {
        Plan Plan = PlanRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Plan not found"));

        if (PlanParticipantRepository.getUserRole(userId, Plan.getId()) != ParticipantRole.OWNER) {
            throw new UnauthorizedException("Only Plan Owner can edit");
        }

        Plan newPPlan = PlanMapper.toEntity(PlanDTO);
        newPPlan.setId(id);

        return PlanMapper.toDTO(PlanRepository.save(newPPlan));
    }

    public void deletePlan(Integer id, Integer userId) {
        Plan Plan = PlanRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Plan not found"));

        if (PlanParticipantRepository.getUserRole(userId, Plan.getId()) != ParticipantRole.OWNER) {
            throw new UnauthorizedException("Only Plan Owner can delete");
        }

        PlanRepository.deleteById(id);
    }
}