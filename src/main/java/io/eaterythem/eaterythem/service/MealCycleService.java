package io.eaterythem.eaterythem.service;

import io.eaterythem.eaterythem.dto.MealCycleDTO;
import io.eaterythem.eaterythem.dto.MealCycleRecipeDTO;
import io.eaterythem.eaterythem.exception.BadRequestException;
import io.eaterythem.eaterythem.exception.UnauthorizedException;
import io.eaterythem.eaterythem.mapper.BasicUserMapper;
import io.eaterythem.eaterythem.mapper.MealCycleMapper;
import io.eaterythem.eaterythem.mapper.MealCycleRecipeMapper;
import io.eaterythem.eaterythem.model.MealCycle;
import io.eaterythem.eaterythem.model.MealCycleRecipe;
import io.eaterythem.eaterythem.model.User;
import io.eaterythem.eaterythem.repository.MealCycleRecipeRepository;
import io.eaterythem.eaterythem.repository.MealCycleRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@AllArgsConstructor
public class MealCycleService {

    private final MealCycleRepository mealCycleRepository;
    private final MealCycleRecipeRepository mealCycleRecipeRepository;

    private final MealCycleMapper mealCycleMapper;
    private final MealCycleRecipeMapper mealCycleRecipeMapper;
    private final BasicUserMapper basicUserMapper;

    public List<MealCycleDTO> getAllMealCycles() {
        List<MealCycle> mealCycles = mealCycleRepository.findAll();
        List<MealCycleDTO> mealCycleDTOs = mealCycleMapper.toDTO(mealCycles);
        return mealCycleDTOs;
    }

    public List<MealCycleDTO> getMeMealCycles(Integer userId) {
        List<MealCycle> mealCycles = mealCycleRepository.findByUserId(userId);
        List<MealCycleDTO> mealCycleDTOs = mealCycleMapper.toDTO(mealCycles);
        return mealCycleDTOs;
    }

    public MealCycleDTO getMealCycleById(Integer id) {
        MealCycle mealCycle = mealCycleRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("MealCycle not found"));

        return mealCycleMapper.toDTO(mealCycle);
    }

    public MealCycleDTO createMealCycle(MealCycleDTO mealCycleDTO, Integer userId) {
        MealCycle mealCycle = mealCycleMapper.toEntity(mealCycleDTO);
        User user = new User();
        user.setId(userId);
        mealCycle.setUser(user);

        return mealCycleMapper.toDTO(mealCycleRepository.save(mealCycle));
    }

    public MealCycleDTO updateMealCycle(Integer id, MealCycleDTO mealCycleDTO, Integer userId) {
        MealCycle mealCycle = mealCycleRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("MealCycle not found"));

        if (mealCycle.getUser() == null || !mealCycle.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("Only mealCycle creator can edit");
        }

        mealCycle.setName(mealCycleDTO.getName());
        mealCycle.setMealType(mealCycleDTO.getMealType());
        mealCycle.setPublic(mealCycleDTO.isPublic());
        mealCycle.setSharedWith(basicUserMapper.toEntity(mealCycleDTO.getSharedWith()));

        if(mealCycleDTO.getRecipes() != null){
            updateRecipes(id, mealCycleDTO.getRecipes(), userId);
        }

        return mealCycleMapper.toDTO(mealCycleRepository.save(mealCycle));
    }

    public void deleteMealCycle(Integer id, Integer userId) {
        MealCycle mealCycle = mealCycleRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("MealCycle not found"));

        if (mealCycle.getUser() == null || !mealCycle.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("Only mealCycle creator can Delete it");
        }

        List<User> sharedWith = mealCycle.getSharedWith();
        if (sharedWith.isEmpty()) {
            mealCycleRepository.deleteById(id);
        } else {
            mealCycle.setUser(sharedWith.get(0));
            sharedWith.remove(0);
            mealCycle.setSharedWith(sharedWith);
            mealCycleRepository.save(mealCycle);
        }
    }

    @Transactional
    public MealCycleDTO updateRecipes(Integer id, List<MealCycleRecipeDTO> mealCycleRecipeDTOs, Integer userId) {
        MealCycle mealCycle = mealCycleRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("MealCycle not found"));

        if (mealCycle.getUser() == null || !mealCycle.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("Only mealCycle creator can add recipes to it");
        }

        List<MealCycleRecipe> newCycleRecipes = new ArrayList<>();
        List<Integer> newRecipeIds = new ArrayList<>();

        for (MealCycleRecipe recipe : mealCycleRecipeMapper.toEntity(mealCycleRecipeDTOs)) {
            if (recipe.getPosition() == null) {
                throw new BadRequestException("Missing recipe position");
            }

            recipe.setCycle(mealCycle);

            if (recipe.getId() == null) {
                recipe = mealCycleRecipeRepository.save(recipe);
            }

            newCycleRecipes.add(recipe);
            if (recipe.getId() != null) {
                newRecipeIds.add(recipe.getId());
            }
        }

        List<MealCycleRecipe> existingRecipes = mealCycle.getRecipes();
        List<MealCycleRecipe> recipesToDelete = new ArrayList<>();
        for (MealCycleRecipe existing : existingRecipes) {
            if (existing.getId() != null && !newRecipeIds.contains(existing.getId())) {
                recipesToDelete.add(existing);
            }
        }
        mealCycleRecipeRepository.deleteAll(recipesToDelete);

        mealCycle.setRecipes(newCycleRecipes);

        mealCycle = mealCycleRepository.save(mealCycle);

        mealCycle = mealCycleRepository.findById(mealCycle.getId()).get();

        return mealCycleMapper.toDTO(mealCycle);
    }

}