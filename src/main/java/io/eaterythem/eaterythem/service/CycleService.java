package io.eaterythem.eaterythem.service;

import io.eaterythem.eaterythem.dto.CycleDTO;
import io.eaterythem.eaterythem.dto.CycleRecipeDTO;
import io.eaterythem.eaterythem.exception.BadRequestException;
import io.eaterythem.eaterythem.exception.UnauthorizedException;
import io.eaterythem.eaterythem.mapper.BasicUserMapper;
import io.eaterythem.eaterythem.mapper.CycleMapper;
import io.eaterythem.eaterythem.mapper.CycleRecipeMapper;
import io.eaterythem.eaterythem.model.Cycle;
import io.eaterythem.eaterythem.model.CycleRecipe;
import io.eaterythem.eaterythem.model.User;
import io.eaterythem.eaterythem.repository.CycleRecipeRepository;
import io.eaterythem.eaterythem.repository.CycleRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@AllArgsConstructor
public class CycleService {

    private final CycleRepository CycleRepository;
    private final CycleRecipeRepository CycleRecipeRepository;

    private final CycleMapper CycleMapper;
    private final CycleRecipeMapper CycleRecipeMapper;
    private final BasicUserMapper basicUserMapper;

    public List<CycleDTO> getAllCycles() {
        List<Cycle> Cycles = CycleRepository.findAll();
        List<CycleDTO> CycleDTOs = CycleMapper.toDTO(Cycles);
        return CycleDTOs;
    }

    public List<CycleDTO> getMeCycles(Integer userId) {
        List<Cycle> Cycles = CycleRepository.findByUserId(userId);
        List<CycleDTO> CycleDTOs = CycleMapper.toDTO(Cycles);
        return CycleDTOs;
    }

    public CycleDTO getCycleById(Integer id) {
        Cycle Cycle = CycleRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Cycle not found"));

        return CycleMapper.toDTO(Cycle);
    }

    public CycleDTO createCycle(CycleDTO CycleDTO, Integer userId) {
        Cycle Cycle = CycleMapper.toEntity(CycleDTO);
        User user = new User();
        user.setId(userId);
        Cycle.setUser(user);

        return CycleMapper.toDTO(CycleRepository.save(Cycle));
    }

    public CycleDTO updateCycle(Integer id, CycleDTO CycleDTO, Integer userId) {
        Cycle Cycle = CycleRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Cycle not found"));

        if (Cycle.getUser() == null || !Cycle.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("Only Cycle creator can edit");
        }

        Cycle.setName(CycleDTO.getName());
        Cycle.setMealType(CycleDTO.getMealType());
        Cycle.setPublic(CycleDTO.isPublic());
        Cycle.setSharedWith(basicUserMapper.toEntity(CycleDTO.getSharedWith()));

        if(CycleDTO.getRecipes() != null){
            updateRecipes(id, CycleDTO.getRecipes(), userId);
        }

        return CycleMapper.toDTO(CycleRepository.save(Cycle));
    }

    public void deleteCycle(Integer id, Integer userId) {
        Cycle Cycle = CycleRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Cycle not found"));

        if (Cycle.getUser() == null || !Cycle.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("Only Cycle creator can Delete it");
        }

        List<User> sharedWith = Cycle.getSharedWith();
        if (sharedWith.isEmpty()) {
            CycleRepository.deleteById(id);
        } else {
            Cycle.setUser(sharedWith.get(0));
            sharedWith.remove(0);
            Cycle.setSharedWith(sharedWith);
            CycleRepository.save(Cycle);
        }
    }

    @Transactional
    public CycleDTO updateRecipes(Integer id, List<CycleRecipeDTO> CycleRecipeDTOs, Integer userId) {
        Cycle Cycle = CycleRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Cycle not found"));

        if (Cycle.getUser() == null || !Cycle.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("Only Cycle creator can add recipes to it");
        }

        List<CycleRecipe> newCycleRecipes = new ArrayList<>();
        List<Integer> newRecipeIds = new ArrayList<>();

        for (CycleRecipe recipe : CycleRecipeMapper.toEntity(CycleRecipeDTOs)) {
            if (recipe.getPosition() == null) {
                throw new BadRequestException("Missing recipe position");
            }

            recipe.setCycle(Cycle);

            if (recipe.getId() == null) {
                recipe = CycleRecipeRepository.save(recipe);
            }

            newCycleRecipes.add(recipe);
            if (recipe.getId() != null) {
                newRecipeIds.add(recipe.getId());
            }
        }

        List<CycleRecipe> existingRecipes = Cycle.getRecipes();
        List<CycleRecipe> recipesToDelete = new ArrayList<>();
        for (CycleRecipe existing : existingRecipes) {
            if (existing.getId() != null && !newRecipeIds.contains(existing.getId())) {
                recipesToDelete.add(existing);
            }
        }
        CycleRecipeRepository.deleteAll(recipesToDelete);

        Cycle.setRecipes(newCycleRecipes);

        Cycle = CycleRepository.save(Cycle);

        Cycle = CycleRepository.findById(Cycle.getId()).get();

        return CycleMapper.toDTO(Cycle);
    }

}