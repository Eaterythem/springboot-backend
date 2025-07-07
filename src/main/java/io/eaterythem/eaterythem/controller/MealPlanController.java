package io.eaterythem.eaterythem.controller;

import io.eaterythem.eaterythem.dto.MealPlanDTO;
import io.eaterythem.eaterythem.service.MealPlanService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/meal-plans")
public class MealPlanController {
    private final MealPlanService mealPlanService;

    public MealPlanController(MealPlanService mealPlanService) {
        this.mealPlanService = mealPlanService;
    }

    @GetMapping
    public List<MealPlanDTO> getAllMealPlans() {
        return mealPlanService.getAllMealPlans();
    }

    @GetMapping("/{id}")
    public MealPlanDTO getMealPlanById(@PathVariable UUID id) {
        return mealPlanService.getMealPlanById(id);
    }

    @PostMapping
    public MealPlanDTO createMealPlan(@RequestBody MealPlanDTO mealPlanDTO) {
        return mealPlanService.createMealPlan(mealPlanDTO);
    }

    @PutMapping("/{id}")
    public MealPlanDTO updateMealPlan(@PathVariable UUID id, @RequestBody MealPlanDTO mealPlanDTO) {
        return mealPlanService.updateMealPlan(id, mealPlanDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteMealPlan(@PathVariable UUID id) {
        mealPlanService.deleteMealPlan(id);
    }
} 