package io.eaterythem.eaterythem.service;

import io.eaterythem.eaterythem.dto.CycleDTO;
import io.eaterythem.eaterythem.dto.CycleRecipeDTO;
import io.eaterythem.eaterythem.exception.BadRequestException;
import io.eaterythem.eaterythem.exception.UnauthorizedException;
import io.eaterythem.eaterythem.mapper.CycleMapper;
import io.eaterythem.eaterythem.mapper.CycleRecipeMapper;
import io.eaterythem.eaterythem.model.Cycle;
import io.eaterythem.eaterythem.model.CycleRecipe;
import io.eaterythem.eaterythem.model.User;
import io.eaterythem.eaterythem.repository.CycleRecipeRepository;
import io.eaterythem.eaterythem.repository.CycleRepository;
import io.eaterythem.eaterythem.tools.ObjectMerger;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@AllArgsConstructor
public class CycleService {

    private final CycleRepository cycleRepository;
    private final CycleRecipeRepository cycleRecipeRepository;

    private final CycleMapper cycleMapper;
    private final CycleRecipeMapper cycleRecipeMapper;

    public List<CycleDTO> getAllCycles() {
        List<Cycle> cycles = cycleRepository.findAll();
        List<CycleDTO> cycleDTOs = cycleMapper.toDTO(cycles);
        return cycleDTOs;
    }

    public List<CycleDTO> getMeCycles(Integer userId) {
        List<Cycle> cycles = cycleRepository.findByUserId(userId);
        List<CycleDTO> cycleDTOs = cycleMapper.toDTO(cycles);
        return cycleDTOs;
    }

    public CycleDTO getCycleById(Integer id) {
        Cycle cycle = cycleRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Cycle not found"));

        return cycleMapper.toDTO(cycle);
    }

    @Transactional
    public CycleDTO createCycle(CycleDTO cycleDTO, Integer userId) {
        Cycle cycle = cycleMapper.toEntity(cycleDTO);
        User user = new User();
        user.setId(userId);
        cycle.setUser(user);

        return cycleMapper.toDTO(cycleRepository.save(cycle));
    }

    @Transactional
    public CycleDTO updateCycle(Integer id, CycleDTO cycleDTO, Integer userId) {
        Cycle cycle = cycleRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Cycle not found"));

        if (cycle.getUser() == null || !cycle.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("Only Cycle creator can edit");
        }
        
        cycle = ObjectMerger.mergeNonNullFields(cycle, cycleMapper.toEntity(cycleDTO));

        if(cycleDTO.getRecipes() != null){
            updateRecipes(id, cycleDTO.getRecipes(), userId);
        }

        return cycleMapper.toDTO(cycleRepository.save(cycle));
    }

    @Transactional
    public void deleteCycle(Integer id, Integer userId) {
        Cycle cycle = cycleRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Cycle not found"));

        if (cycle.getUser() == null || !cycle.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("Only Cycle creator can Delete it");
        }

        List<User> sharedWith = cycle.getSharedWith();
        if (sharedWith.isEmpty()) {
            cycleRepository.deleteById(id);
        } else {
            cycle.setUser(sharedWith.get(0));
            sharedWith.remove(0);
            cycle.setSharedWith(sharedWith);
            cycleRepository.save(cycle);
        }
    }

    @Transactional
    public CycleDTO updateRecipes(Integer id, List<CycleRecipeDTO> cycleRecipeDTOs, Integer userId) {
        Cycle cycle = cycleRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Cycle not found"));

        if (cycle.getUser() == null || !cycle.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("Only Cycle creator can add recipes to it");
        }

        List<CycleRecipe> newCycleRecipes = new ArrayList<>();
        List<Integer> newRecipeIds = new ArrayList<>();

        for (CycleRecipe cycleRecipe : cycleRecipeMapper.toEntity(cycleRecipeDTOs)) {
            if (cycleRecipe.getPosition() == null) {
                throw new BadRequestException("Missing recipe position");
            }

            cycleRecipe.setCycle(cycle);

            if (cycleRecipe.getId() == null) {
                cycleRecipe = cycleRecipeRepository.save(cycleRecipe);
            }

            newCycleRecipes.add(cycleRecipe);
            if (cycleRecipe.getId() != null) {
                newRecipeIds.add(cycleRecipe.getId());
            }
        }

        List<CycleRecipe> existingRecipes = cycle.getRecipes();
        List<CycleRecipe> recipesToDelete = new ArrayList<>();
        for (CycleRecipe existing : existingRecipes) {
            if (existing.getId() != null && !newRecipeIds.contains(existing.getId())) {
                recipesToDelete.add(existing);
            }
        }
        cycleRecipeRepository.deleteAll(recipesToDelete);

        cycle.setRecipes(newCycleRecipes);

        cycle = cycleRepository.save(cycle);

        cycle = cycleRepository.findById(cycle.getId()).get();

        return cycleMapper.toDTO(cycle);
    }

}