package io.eaterythem.eaterythem.service;

import io.eaterythem.eaterythem.dto.MealEntryDTO;
import io.eaterythem.eaterythem.exception.BadRequestException;
import io.eaterythem.eaterythem.exception.UnauthorizedException;
import io.eaterythem.eaterythem.mapper.MealEntryMapper;
import io.eaterythem.eaterythem.model.MealEntry;
import io.eaterythem.eaterythem.model.enums.ParticipantRole;
import io.eaterythem.eaterythem.repository.MealEntryRepository;
import io.eaterythem.eaterythem.repository.MealPlanParticipantRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import java.util.*;

@AllArgsConstructor
@Service
public class MealEntryService {
    MealEntryRepository mealEntryRepository;
    MealPlanParticipantRepository mealPlanParticipantRepository;

    MealEntryMapper mealEntryMapper;

    public List<MealEntryDTO> getAllMealEntries() {
        List<MealEntry> mealEntries = mealEntryRepository.findAll();
        List<MealEntryDTO> mealEntryDTOs = mealEntryMapper.toDTO(mealEntries);
        return mealEntryDTOs;
    }

    public MealEntryDTO getMealEntryById(Integer id) {
        MealEntry mealEntry = mealEntryRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("MealEntry not found"));

        return mealEntryMapper.toDTO(mealEntry);

    }

    public MealEntryDTO createMealEntry(MealEntryDTO mealEntryDTO) {
        MealEntry mealEntry = mealEntryMapper.toEntity(mealEntryDTO);
        return mealEntryMapper.toDTO(mealEntryRepository.save(mealEntry));
    }

    public MealEntryDTO updateMealEntry(Integer id, MealEntryDTO mealEntryDTO, Integer userId) {
        MealEntry mealEntry = mealEntryRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("MealEntry not found"));

        if (mealPlanParticipantRepository.getUserRole(userId, mealEntry.getMealPlan().getId()) != ParticipantRole.OWNER) {
            throw new UnauthorizedException("Only Plan Owner can edit MealEntery");
        }

        MealEntry newMealEntry = mealEntryMapper.toEntity(mealEntryDTO);
        newMealEntry.setId(mealEntry.getId());

        // mealEntry.setDayIndex(newMealEntry.getDayIndex());
        // mealEntry.setMealPlan(newMealEntry.getMealPlan());
        // mealEntry.setMealType(newMealEntry.getMealType());
        // mealEntry.setPlannedRecipe(newMealEntry.getPlannedRecipe());
        // mealEntry.setActualRecipe(newMealEntry.getActualRecipe());
        // mealEntry.setStatus(newMealEntry.getStatus());
        // mealEntry.setNote(newMealEntry.getNote());

        return mealEntryMapper.toDTO(mealEntryRepository.save(newMealEntry));
    }

    public void deleteMealEntry(Integer id, Integer userId) {
        MealEntry mealEntry = mealEntryRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("MealEntry not found"));

        if (mealPlanParticipantRepository.getUserRole(userId, mealEntry.getMealPlan().getId()) != ParticipantRole.OWNER) {
            throw new UnauthorizedException("Only Plan Owner can delete MealEntery");
        }

        mealEntryRepository.deleteById(id);

    }
}