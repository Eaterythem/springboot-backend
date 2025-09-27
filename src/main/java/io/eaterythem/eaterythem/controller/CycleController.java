package io.eaterythem.eaterythem.controller;

import io.eaterythem.eaterythem.dto.CycleDTO;
import io.eaterythem.eaterythem.dto.CycleRecipeDTO;
import io.eaterythem.eaterythem.security.UserPrincipal;
import io.eaterythem.eaterythem.security.annotations.CurrentUser;
import io.eaterythem.eaterythem.service.CycleService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cycles")
public class CycleController {
    private final CycleService CycleService;

    public CycleController(CycleService CycleService) {
        this.CycleService = CycleService;
    }

    @GetMapping
    public List<CycleDTO> getAllCycles() {
        return CycleService.getAllCycles();
    }

    @GetMapping("/me")
    public List<CycleDTO> getMeCycles(@CurrentUser UserPrincipal user) {
        return CycleService.getMeCycles(user.getUserId());
    }

    @GetMapping("/{id}")
    public CycleDTO getCycleById(@PathVariable Integer id) {
        return CycleService.getCycleById(id);
    }

    @PostMapping
    public CycleDTO createCycle(@RequestBody CycleDTO CycleDTO, @CurrentUser UserPrincipal user) {
        return CycleService.createCycle(CycleDTO, user.getUserId());
    }

    @PutMapping("/{id}")
    public CycleDTO updateCycle(@PathVariable Integer id, @RequestBody CycleDTO CycleDTO,
            @CurrentUser UserPrincipal user) {
        return CycleService.updateCycle(id, CycleDTO, user.getUserId());
    }

    @PatchMapping("/{id}/recipes")
    public CycleDTO updateCycleRecipes(@PathVariable Integer id, @RequestBody  List<CycleRecipeDTO> CycleRecipeDTOs,
            @CurrentUser UserPrincipal user) {
        return CycleService.updateRecipes(id, CycleRecipeDTOs, user.getUserId());
    }

    @DeleteMapping("/{id}")
    public void deleteCycle(@PathVariable Integer id, @CurrentUser UserPrincipal user) {
        CycleService.deleteCycle(id, user.getUserId());
    }
}
