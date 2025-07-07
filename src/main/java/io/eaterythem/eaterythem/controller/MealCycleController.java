package io.eaterythem.eaterythem.controller;

import io.eaterythem.eaterythem.dto.MealCycleDTO;
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

    @GetMapping("/{id}")
    public MealCycleDTO getMealCycleById(@PathVariable UUID id) {
        return mealCycleService.getMealCycleById(id);
    }

    @PostMapping
    public MealCycleDTO createMealCycle(@RequestBody MealCycleDTO mealCycleDTO) {
        return mealCycleService.createMealCycle(mealCycleDTO);
    }

    @PutMapping("/{id}")
    public MealCycleDTO updateMealCycle(@PathVariable UUID id, @RequestBody MealCycleDTO mealCycleDTO) {
        return mealCycleService.updateMealCycle(id, mealCycleDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteMealCycle(@PathVariable UUID id) {
        mealCycleService.deleteMealCycle(id);
    }
} 