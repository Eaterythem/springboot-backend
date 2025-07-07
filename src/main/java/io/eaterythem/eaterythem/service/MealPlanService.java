package io.eaterythem.eaterythem.service;

import io.eaterythem.eaterythem.dto.MealPlanDTO;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class MealPlanService {
    public List<MealPlanDTO> getAllMealPlans() { return new ArrayList<>(); }
    public MealPlanDTO getMealPlanById(UUID id) { return null; }
    public MealPlanDTO createMealPlan(MealPlanDTO mealPlanDTO) { return null; }
    public MealPlanDTO updateMealPlan(UUID id, MealPlanDTO mealPlanDTO) { return null; }
    public void deleteMealPlan(UUID id) {}
} 