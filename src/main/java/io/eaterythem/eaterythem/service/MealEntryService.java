package io.eaterythem.eaterythem.service;

import io.eaterythem.eaterythem.dto.MealEntryDTO;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class MealEntryService {
    public List<MealEntryDTO> getAllMealEntries() { return new ArrayList<>(); }
    public MealEntryDTO getMealEntryById(UUID id) { return null; }
    public MealEntryDTO createMealEntry(MealEntryDTO mealEntryDTO) { return null; }
    public MealEntryDTO updateMealEntry(UUID id, MealEntryDTO mealEntryDTO) { return null; }
    public void deleteMealEntry(UUID id) {}
} 