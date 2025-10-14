package io.eaterythem.eaterythem.controller;

import io.eaterythem.eaterythem.dto.CycleDTO;
import io.eaterythem.eaterythem.dto.CycleRecipeDTO;
import io.eaterythem.eaterythem.security.UserPrincipal;
import io.eaterythem.eaterythem.security.annotations.CurrentUser;
import io.eaterythem.eaterythem.service.CycleService;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;
import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/api/cycles")
public class CycleController {
    private final CycleService cycleService;

    @GetMapping
    public List<CycleDTO> getAllCycles() {
        return cycleService.getAllCycles();
    }

    @GetMapping("/me")
    public List<CycleDTO> getMeCycles(@CurrentUser UserPrincipal user) {
        return cycleService.getMeCycles(user.getUserId());
    }

    @GetMapping("/{id}")
    public CycleDTO getCycleById(@PathVariable Integer id) {
        return cycleService.getCycleById(id);
    }

    @PostMapping
    public CycleDTO createCycle(@RequestBody CycleDTO cycleDTO, @CurrentUser UserPrincipal user) {
        return cycleService.createCycle(cycleDTO, user.getUserId());
    }

    @PutMapping("/{id}")
    public CycleDTO updateCycle(@PathVariable Integer id, @RequestBody CycleDTO cycleDTO,
            @CurrentUser UserPrincipal user) {
        return cycleService.updateCycle(id, cycleDTO, user.getUserId());
    }

    @PatchMapping("/{id}/recipes")
    public CycleDTO updateCycleRecipes(@PathVariable Integer id, @RequestBody  List<CycleRecipeDTO> cycleRecipeDTOs,
            @CurrentUser UserPrincipal user) {
        return cycleService.updateRecipes(id, cycleRecipeDTOs, user.getUserId());
    }

    @DeleteMapping("/{id}")
    public void deleteCycle(@PathVariable Integer id, @CurrentUser UserPrincipal user) {
        cycleService.deleteCycle(id, user.getUserId());
    }
}
