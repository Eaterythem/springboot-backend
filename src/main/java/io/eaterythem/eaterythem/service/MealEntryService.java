package io.eaterythem.eaterythem.service;

import io.eaterythem.eaterythem.dto.MealEntryDTO;
import io.eaterythem.eaterythem.dto.MealVoteDTO;
import io.eaterythem.eaterythem.exception.BadRequestException;
import io.eaterythem.eaterythem.exception.UnauthorizedException;
import io.eaterythem.eaterythem.mapper.MealEntryMapper;
import io.eaterythem.eaterythem.model.MealEntry;
import io.eaterythem.eaterythem.model.MealPlanParticipant;
import io.eaterythem.eaterythem.model.MealVote;
import io.eaterythem.eaterythem.model.User;
import io.eaterythem.eaterythem.model.enums.ParticipantRole;
import io.eaterythem.eaterythem.repository.MealEntryRepository;
import io.eaterythem.eaterythem.repository.MealPlanParticipantRepository;
import io.eaterythem.eaterythem.repository.MealPlanRepository;
import io.eaterythem.eaterythem.repository.RecipeRepository;
import io.eaterythem.eaterythem.tools.ObjectMerger;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import java.util.*;

@AllArgsConstructor
@Service
public class MealEntryService {

    RecipeRepository recipeRepository;
    MealEntryRepository mealEntryRepository;
    MealPlanRepository mealPlanRepository;
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

        if (mealPlanParticipantRepository.getUserRole(userId,
                mealEntry.getMealPlan().getId()) != ParticipantRole.OWNER) {
            throw new UnauthorizedException("Only Plan Owner can edit MealEntery");
        }

        System.out.println(mealEntryMapper.toEntity(mealEntryDTO));
        MealEntry newMealEntry = ObjectMerger.mergeNonNullFields(mealEntry, mealEntryMapper.toEntity(mealEntryDTO));

        return mealEntryMapper.toDTO(mealEntryRepository.save(newMealEntry));
    }

    public void deleteMealEntry(Integer id, Integer userId) {
        MealEntry mealEntry = mealEntryRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("MealEntry not found"));

        if (mealPlanParticipantRepository.getUserRole(userId,
                mealEntry.getMealPlan().getId()) != ParticipantRole.OWNER) {
            throw new UnauthorizedException("Only Plan Owner can delete MealEntery");
        }

        mealEntryRepository.deleteById(id);

    }

    public MealEntryDTO vote(Integer mealEntryId, MealVoteDTO voteDTO, Integer userId) {
        MealEntry mealEntry = mealEntryRepository.findById(mealEntryId)
                .orElseThrow(() -> new BadRequestException("Entry Not Found"));

        MealPlanParticipant participant = mealEntry.getMealPlan().getParticipants().stream()
                .filter(p -> p.getUser().getId().equals(userId))
                .findFirst()
                .orElse(null);

        if (participant == null || participant.getRole() == ParticipantRole.VIEWER) {
            throw new BadRequestException("User not allowed to vote");
        }

        // 🔑 Check if the user has already voted
        MealVote existingVote = mealEntry.getVotes().stream()
                .filter(v -> v.getUser().getId().equals(userId))
                .findFirst()
                .orElse(null);

        if (existingVote != null) {
            // update existing vote
            existingVote.setVoteType(voteDTO.getVoteType());
            existingVote.setNote(voteDTO.getNote());
            if (voteDTO.getReplacementRecipe() != null)
            existingVote.setReplacementRecipe(recipeRepository.findById(voteDTO.getReplacementRecipe().getId()).orElse(null));
        } else {
            mealEntry.getVotes().add(MealVote.builder()
                    .mealEntry(mealEntry)
                    .voteType(voteDTO.getVoteType())
                    .note(voteDTO.getNote())
                    .user(User.builder().id(userId).build())
                    .replacementRecipe(voteDTO.getReplacementRecipe() == null? null : recipeRepository.findById(voteDTO.getReplacementRecipe().getId()).orElse(null))
                    .build());

        }

        return mealEntryMapper.toDTO(mealEntryRepository.save(mealEntry));
    }
}