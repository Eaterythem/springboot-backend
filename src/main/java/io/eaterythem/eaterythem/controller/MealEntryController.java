package io.eaterythem.eaterythem.controller;

import io.eaterythem.eaterythem.dto.MealEntryDTO;
import io.eaterythem.eaterythem.service.MealEntryService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/meal-entries")
public class MealEntryController {
    private final MealEntryService mealEntryService;

    public MealEntryController(MealEntryService mealEntryService) {
        this.mealEntryService = mealEntryService;
    }

    @GetMapping
    public List<MealEntryDTO> getAllMealEntries() {
        return mealEntryService.getAllMealEntries();
    }

    @GetMapping("/{id}")
    public MealEntryDTO getMealEntryById(@PathVariable UUID id) {
        return mealEntryService.getMealEntryById(id);
    }

    @PostMapping
    public MealEntryDTO createMealEntry(@RequestBody MealEntryDTO mealEntryDTO) {
        return mealEntryService.createMealEntry(mealEntryDTO);
    }

    @PutMapping("/{id}")
    public MealEntryDTO updateMealEntry(@PathVariable UUID id, @RequestBody MealEntryDTO mealEntryDTO) {
        return mealEntryService.updateMealEntry(id, mealEntryDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteMealEntry(@PathVariable UUID id) {
        mealEntryService.deleteMealEntry(id);
    }
} 