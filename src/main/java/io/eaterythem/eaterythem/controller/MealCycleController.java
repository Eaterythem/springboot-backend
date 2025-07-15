package io.eaterythem.eaterythem.controller;

import io.eaterythem.eaterythem.dto.MealCycleDTO;
import io.eaterythem.eaterythem.dto.MealCycleRecipeDTO;
import io.eaterythem.eaterythem.security.UserPrincipal;
import io.eaterythem.eaterythem.security.annotations.CurrentUser;
import io.eaterythem.eaterythem.service.MealCycleService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/meal-cycles")
public class MealCycleController {
    private final MealCycleService mealCycleService;

    public MealCycleController(MealCycleService mealCycleService) {
        this.mealCycleService = mealCycleService;
    }

    @GetMapping
    public List<MealCycleDTO> getAllMealCycles() {
        return mealCycleService.getAllMealCycles();
    }

    @GetMapping("/me")
    public List<MealCycleDTO> getMeMealCycles(@CurrentUser UserPrincipal user) {
        return mealCycleService.getMeMealCycles(user.getUserId());
    }

    @GetMapping("/{id}")
    public MealCycleDTO getMealCycleById(@PathVariable UUID id) {
        return mealCycleService.getMealCycleById(id);
    }

    @PostMapping
    public MealCycleDTO createMealCycle(@RequestBody MealCycleDTO mealCycleDTO, @CurrentUser UserPrincipal user) {
        return mealCycleService.createMealCycle(mealCycleDTO, user.getUserId());
    }

    @PutMapping("/{id}")
    public MealCycleDTO updateMealCycle(@PathVariable UUID id, @RequestBody MealCycleDTO mealCycleDTO,
            @CurrentUser UserPrincipal user) {
        return mealCycleService.updateMealCycle(id, mealCycleDTO, user.getUserId());
    }

    @PatchMapping("/{id}/recipes")
    public MealCycleDTO updateMealCycleRecipes(@PathVariable UUID id, @RequestBody  List<MealCycleRecipeDTO> mealCycleRecipeDTOs,
            @CurrentUser UserPrincipal user) {
        return mealCycleService.updateRecipes(id, mealCycleRecipeDTOs, user.getUserId());
    }

    @DeleteMapping("/{id}")
    public void deleteMealCycle(@PathVariable UUID id, @CurrentUser UserPrincipal user) {
        mealCycleService.deleteMealCycle(id, user.getUserId());
    }
}
