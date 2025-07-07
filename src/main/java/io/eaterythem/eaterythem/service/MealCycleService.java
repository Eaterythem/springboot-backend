package io.eaterythem.eaterythem.service;

import io.eaterythem.eaterythem.dto.MealCycleDTO;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class MealCycleService {
    public List<MealCycleDTO> getAllMealCycles() { return new ArrayList<>(); }
    public MealCycleDTO getMealCycleById(UUID id) { return null; }
    public MealCycleDTO createMealCycle(MealCycleDTO mealCycleDTO) { return null; }
    public MealCycleDTO updateMealCycle(UUID id, MealCycleDTO mealCycleDTO) { return null; }
    public void deleteMealCycle(UUID id) {}
} 