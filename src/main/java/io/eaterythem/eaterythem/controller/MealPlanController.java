package io.eaterythem.eaterythem.controller;

import io.eaterythem.eaterythem.dto.MealPlanDTO;
import io.eaterythem.eaterythem.security.UserPrincipal;
import io.eaterythem.eaterythem.security.annotations.CurrentUser;
import io.eaterythem.eaterythem.service.MealPlanService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;


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

    @GetMapping("/me")
    public List<MealPlanDTO> getMePlans(@CurrentUser UserPrincipal user) {
        return mealPlanService.getMePlans(user.getUserId());
    }
    

    @PostMapping
    public MealPlanDTO createMealPlan(@RequestBody MealPlanDTO mealPlanDTO, @CurrentUser UserPrincipal user) {
        return mealPlanService.createMealPlan(mealPlanDTO, user.getUserId());
    }

    @PutMapping("/{id}")
    public MealPlanDTO updateMealPlan(@PathVariable UUID id, @RequestBody MealPlanDTO mealPlanDTO, @CurrentUser UserPrincipal user) {
        return mealPlanService.updateMealPlan(id, mealPlanDTO, user.getUserId());
    }

    @DeleteMapping("/{id}")
    public void deleteMealPlan(@PathVariable UUID id, @CurrentUser UserPrincipal user) {
        mealPlanService.deleteMealPlan(id, user.getUserId());
    }
} 