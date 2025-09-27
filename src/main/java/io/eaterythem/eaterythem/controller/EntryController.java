package io.eaterythem.eaterythem.controller;

import io.eaterythem.eaterythem.dto.EntryDTO;
import io.eaterythem.eaterythem.dto.VoteDTO;
import io.eaterythem.eaterythem.security.UserPrincipal;
import io.eaterythem.eaterythem.security.annotations.CurrentUser;
import io.eaterythem.eaterythem.service.EntryService;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/entries")
public class EntryController {
    private final EntryService entryService;

    @GetMapping
    public List<EntryDTO> getAllEntries() {
        return entryService.getAllEntries();
    }
    
    @PostMapping("/{id}/vote")
    public EntryDTO voteEntry(@PathVariable Integer id, @RequestBody VoteDTO voteDTO, @CurrentUser UserPrincipal user) {
        return entryService.vote(id, voteDTO, user.getUserId());
    }

    @GetMapping("/{id}")
    public EntryDTO getEntryById(@PathVariable Integer id) {
        return entryService.getEntryById(id);
    }

    @PostMapping
    public EntryDTO createEntry(@RequestBody EntryDTO EntryDTO) {
        return entryService.createEntry(EntryDTO);
    }

    @PutMapping("/{id}")
    public EntryDTO updateEntry(@PathVariable Integer id, @RequestBody EntryDTO EntryDTO, @CurrentUser UserPrincipal user) {
        return entryService.updateEntry(id, EntryDTO, user.getUserId());
    }

    @DeleteMapping("/{id}")
    public void deleteEntry(@PathVariable Integer id, @CurrentUser UserPrincipal user) {
        entryService.deleteEntry(id, user.getUserId());
    }
}