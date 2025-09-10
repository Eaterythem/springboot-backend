package io.eaterythem.eaterythem.mapper;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import io.eaterythem.eaterythem.dto.MealPlanDTO;
import io.eaterythem.eaterythem.model.MealEntry;
import io.eaterythem.eaterythem.model.MealPlan;
import io.eaterythem.eaterythem.model.enums.MealEntryStatus;
import io.eaterythem.eaterythem.model.enums.MealType;
import io.eaterythem.eaterythem.repository.MealPlanRepository;

@Primary
@Component("mealPlanMapper")
public class MealPlanMapperDecorator implements MealPlanMapper {

    private final MealPlanMapper delegate;
    private final MealEntryMapper mealEntryMapper;
    private final MealPlanRepository mealPlanRepository;

    public MealPlanMapperDecorator(
            @Qualifier("mealPlanMapperImpl") MealPlanMapper delegate,
            MealEntryMapper mealEntryMapper,
            MealPlanRepository mealPlanRepository
    ) {
        this.delegate = delegate;
        this.mealEntryMapper = mealEntryMapper;
        this.mealPlanRepository = mealPlanRepository;
    }

    @Override
    public MealPlanDTO toDTO(MealPlan mealPlan) {
        MealPlanDTO dto = delegate.toDTO(mealPlan);
        dto.setBreakfastEntry(mealEntryMapper.toDTO(getOrCreateEntry(mealPlan, MealType.BREAKFAST)));
        dto.setLunchEntry(mealEntryMapper.toDTO(getOrCreateEntry(mealPlan, MealType.LUNCH)));
        dto.setDinnerEntry(mealEntryMapper.toDTO(getOrCreateEntry(mealPlan, MealType.DINNER)));
        dto.setEntries(mealEntryMapper.toDTO(mealPlan.getMealEntries()));
        return dto;
    }

    @Override
    public List<MealPlanDTO> toDTO(List<MealPlan> mealPlans) {
        return mealPlans.stream().map(this::toDTO).toList();
    }

    @Override
    public MealPlan toEntity(MealPlanDTO mealPlanDTO) {
        return delegate.toEntity(mealPlanDTO);
    }

    private MealEntry getOrCreateEntry(MealPlan mealPlan, MealType mealType) {
        return mealPlan.getMealEntries().stream()
                .filter(e -> e.getStatus() == MealEntryStatus.PENDING
                        && e.getPlannedRecipe().getMealType() == mealType)
                .max(Comparator.comparingInt(MealEntry::getDayIndex))
                .orElseGet(() -> {
                    int index;
                    var cycle = switch (mealType) {
                        case BREAKFAST -> {
                            index = mealPlan.getBreakfastIndex();
                            mealPlan.setBreakfastIndex(index + 1);
                            yield mealPlan.getBreakfastCycle();
                        }
                        case LUNCH -> {
                            index = mealPlan.getLunchIndex();
                            mealPlan.setLunchIndex(index + 1);
                            yield mealPlan.getLunchCycle();
                        }
                        case DINNER -> {
                            index = mealPlan.getDinnerIndex();
                            mealPlan.setDinnerIndex(index + 1);
                            yield mealPlan.getDinnerCycle();
                        }
                    };

                    var recipe = cycle.getRecipes()
                            .get(index % cycle.getRecipes().size())
                            .getRecipe();

                    MealEntry newEntry = new MealEntry(
                            null,
                            index,
                            MealEntryStatus.PENDING,
                            "",
                            mealPlan,
                            recipe,
                            null,
                            null);

                    List<MealEntry> entries = mealPlan.getMealEntries();
                    entries.add(newEntry);
                    mealPlan.setMealEntries(entries);

                    return mealPlanRepository.save(mealPlan) // cascade saves entry
                            .getMealEntries()
                            .get(mealPlan.getMealEntries().size() - 1);
                });
    }

}
