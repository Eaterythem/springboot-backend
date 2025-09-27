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
    EntryRepository EntryRepository;
    PlanRepository PlanRepository;
    PlanParticipantRepository PlanParticipantRepository;

    EntryMapper EntryMapper;

    public List<EntryDTO> getAllEntries() {
        List<Entry> Entries = EntryRepository.findAll();
        List<EntryDTO> EntryDTOs = EntryMapper.toDTO(Entries);
        return EntryDTOs;
    }

    public EntryDTO getEntryById(Integer id) {
        Entry Entry = EntryRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Entry not found"));

        return EntryMapper.toDTO(Entry);

    }

    public EntryDTO createEntry(EntryDTO EntryDTO) {
        Entry Entry = EntryMapper.toEntity(EntryDTO);
        return EntryMapper.toDTO(EntryRepository.save(Entry));
    }

    public EntryDTO updateEntry(Integer id, EntryDTO EntryDTO, Integer userId) {
        Entry Entry = EntryRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Entry not found"));

        if (PlanParticipantRepository.getUserRole(userId,
                Entry.getPlan().getId()) != ParticipantRole.OWNER) {
            throw new UnauthorizedException("Only Plan Owner can edit Entery");
        }

        Entry newEntry = ObjectMerger.mergeNonNullFields(Entry, EntryMapper.toEntity(EntryDTO));

        return EntryMapper.toDTO(EntryRepository.save(newEntry));
    }

    public void deleteEntry(Integer id, Integer userId) {
        Entry Entry = EntryRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Entry not found"));

        if (PlanParticipantRepository.getUserRole(userId,
                Entry.getPlan().getId()) != ParticipantRole.OWNER) {
            throw new UnauthorizedException("Only Plan Owner can delete Entery");
        }

        EntryRepository.deleteById(id);

    }

    public EntryDTO vote(Integer EntryId, VoteDTO voteDTO, Integer userId) {
        Entry Entry = EntryRepository.findById(EntryId)
                .orElseThrow(() -> new BadRequestException("Entry Not Found"));

        PlanParticipant participant = Entry.getPlan().getParticipants().stream()
                .filter(p -> p.getUser().getId().equals(userId))
                .findFirst()
                .orElse(null);

        if (participant == null || participant.getRole() == ParticipantRole.VIEWER) {
            throw new BadRequestException("User not allowed to vote");
        }

        // 🔑 Check if the user has already voted
        Vote existingVote = Entry.getVotes().stream()
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
            Entry.getVotes().add(Vote.builder()
                    .entry(Entry)
                    .voteType(voteDTO.getVoteType())
                    .note(voteDTO.getNote())
                    .user(User.builder().id(userId).build())
                    .replacementRecipe(voteDTO.getReplacementRecipe() == null? null : recipeRepository.findById(voteDTO.getReplacementRecipe().getId()).orElse(null))
                    .build());

        }

        return EntryMapper.toDTO(EntryRepository.save(Entry));
    }
}