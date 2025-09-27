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
    private final EntryService EntryService;

    @GetMapping
    public List<EntryDTO> getAllEntries() {
        return EntryService.getAllEntries();
    }
    
    @PostMapping("/{id}/vote")
    public EntryDTO voteEntry(@PathVariable Integer id, @RequestBody VoteDTO voteDTO, @CurrentUser UserPrincipal user) {
        return EntryService.vote(id, voteDTO, user.getUserId());
    }

    @GetMapping("/{id}")
    public EntryDTO getEntryById(@PathVariable Integer id) {
        return EntryService.getEntryById(id);
    }

    @PostMapping
    public EntryDTO createEntry(@RequestBody EntryDTO EntryDTO) {
        return EntryService.createEntry(EntryDTO);
    }

    @PutMapping("/{id}")
    public EntryDTO updateEntry(@PathVariable Integer id, @RequestBody EntryDTO EntryDTO, @CurrentUser UserPrincipal user) {
        return EntryService.updateEntry(id, EntryDTO, user.getUserId());
    }

    @DeleteMapping("/{id}")
    public void deleteEntry(@PathVariable Integer id, @CurrentUser UserPrincipal user) {
        EntryService.deleteEntry(id, user.getUserId());
    }
}