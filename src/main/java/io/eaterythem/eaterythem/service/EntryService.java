package io.eaterythem.eaterythem.service;

import io.eaterythem.eaterythem.dto.EntryDTO;
import io.eaterythem.eaterythem.dto.VoteDTO;
import io.eaterythem.eaterythem.exception.BadRequestException;
import io.eaterythem.eaterythem.exception.UnauthorizedException;
import io.eaterythem.eaterythem.mapper.EntryMapper;
import io.eaterythem.eaterythem.model.Entry;
import io.eaterythem.eaterythem.model.PlanParticipant;
import io.eaterythem.eaterythem.model.Vote;
import io.eaterythem.eaterythem.model.User;
import io.eaterythem.eaterythem.model.enums.ParticipantRole;
import io.eaterythem.eaterythem.repository.EntryRepository;
import io.eaterythem.eaterythem.repository.PlanParticipantRepository;
import io.eaterythem.eaterythem.repository.PlanRepository;
import io.eaterythem.eaterythem.repository.RecipeRepository;
import io.eaterythem.eaterythem.tools.ObjectMerger;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import java.util.*;

@AllArgsConstructor
@Service
public class EntryService {

    RecipeRepository recipeRepository;
    EntryRepository entryRepository;
    PlanRepository planRepository;
    PlanParticipantRepository planParticipantRepository;

    EntryMapper entryMapper;

    public List<EntryDTO> getAllEntries() {
        List<Entry> entries = entryRepository.findAll();
        List<EntryDTO> entryDTOs = entryMapper.toDTO(entries);
        return entryDTOs;
    }

    public EntryDTO getEntryById(Integer id) {
        Entry entry = entryRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Entry not found"));

        return entryMapper.toDTO(entry);

    }

    public EntryDTO createEntry(EntryDTO entryDTO) {
        Entry entry = entryMapper.toEntity(entryDTO);
        return entryMapper.toDTO(entryRepository.save(entry));
    }

    public EntryDTO updateEntry(Integer id, EntryDTO entryDTO, Integer userId) {
        Entry entry = entryRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Entry not found"));

        if (planParticipantRepository.getUserRole(userId,
                entry.getPlan().getId()) != ParticipantRole.OWNER) {
            throw new UnauthorizedException("Only Plan Owner can edit Entery");
        }

        Entry newEntry = ObjectMerger.mergeNonNullFields(entry, entryMapper.toEntity(entryDTO));

        return entryMapper.toDTO(entryRepository.save(newEntry));
    }

    public void deleteEntry(Integer id, Integer userId) {
        Entry entry = entryRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Entry not found"));

        if (planParticipantRepository.getUserRole(userId,
                entry.getPlan().getId()) != ParticipantRole.OWNER) {
            throw new UnauthorizedException("Only Plan Owner can delete Entery");
        }

        entryRepository.deleteById(id);

    }

    public EntryDTO vote(Integer entryId, VoteDTO voteDTO, Integer userId) {
        Entry entry = entryRepository.findById(entryId)
                .orElseThrow(() -> new BadRequestException("Entry Not Found"));

        PlanParticipant participant = entry.getPlan().getParticipants().stream()
                .filter(p -> p.getUser().getId().equals(userId))
                .findFirst()
                .orElse(null);

        if (participant == null || participant.getRole() == ParticipantRole.VIEWER) {
            throw new BadRequestException("User not allowed to vote");
        }

        // 🔑 Check if the user has already voted
        Vote existingVote = entry.getVotes().stream()
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
            entry.getVotes().add(Vote.builder()
                    .entry(entry)
                    .voteType(voteDTO.getVoteType())
                    .note(voteDTO.getNote())
                    .user(User.builder().id(userId).build())
                    .replacementRecipe(voteDTO.getReplacementRecipe() == null? null : recipeRepository.findById(voteDTO.getReplacementRecipe().getId()).orElse(null))
                    .build());

        }

        return entryMapper.toDTO(entryRepository.save(entry));
    }
}