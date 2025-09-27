package io.eaterythem.eaterythem.mapper;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import io.eaterythem.eaterythem.dto.PlanDTO;
import io.eaterythem.eaterythem.model.Entry;
import io.eaterythem.eaterythem.model.Plan;
import io.eaterythem.eaterythem.model.enums.EntryStatus;
import io.eaterythem.eaterythem.model.enums.MealType;
import io.eaterythem.eaterythem.repository.PlanRepository;

@Primary
@Component("PlanMapper")
public class PlanMapperDecorator implements PlanMapper {

    private final PlanMapper delegate;
    private final EntryMapper entryMapper;
    private final PlanRepository planRepository;

    public PlanMapperDecorator(
            @Qualifier("planMapperImpl") PlanMapper delegate,
            EntryMapper entryMapper,
            PlanRepository planRepository
    ) {
        this.delegate = delegate;
        this.entryMapper = entryMapper;
        this.planRepository = planRepository;
    }

    @Override
    public PlanDTO toDTO(Plan plan) {
        PlanDTO dto = delegate.toDTO(plan);
        dto.setBreakfastEntry(entryMapper.toDTO(getOrCreateEntry(plan, MealType.BREAKFAST)));
        dto.setLunchEntry(entryMapper.toDTO(getOrCreateEntry(plan, MealType.LUNCH)));
        dto.setDinnerEntry(entryMapper.toDTO(getOrCreateEntry(plan, MealType.DINNER)));
        dto.setEntries(entryMapper.toDTO(plan.getEntries()));
        return dto;
    }

    @Override
    public List<PlanDTO> toDTO(List<Plan> plans) {
        return plans.stream().map(this::toDTO).toList();
    }

    @Override
    public Plan toEntity(PlanDTO planDTO) {
        return delegate.toEntity(planDTO);
    }

    private Entry getOrCreateEntry(Plan plan, MealType mealType) {
        return plan.getEntries().stream()
                .filter(e -> e.getStatus() == EntryStatus.PENDING
                        && e.getPlannedRecipe().getMealType() == mealType)
                .max(Comparator.comparingInt(Entry::getDayIndex))
                .orElseGet(() -> {
                    int index;
                    var cycle = switch (mealType) {
                        case BREAKFAST -> {
                            index = plan.getBreakfastIndex();
                            plan.setBreakfastIndex(index + 1);
                            yield plan.getBreakfastCycle();
                        }
                        case LUNCH -> {
                            index = plan.getLunchIndex();
                            plan.setLunchIndex(index + 1);
                            yield plan.getLunchCycle();
                        }
                        case DINNER -> {
                            index = plan.getDinnerIndex();
                            plan.setDinnerIndex(index + 1);
                            yield plan.getDinnerCycle();
                        }
                    };

                    var recipe = cycle.getRecipes()
                            .get(index % cycle.getRecipes().size())
                            .getRecipe();

                    Entry newEntry = new Entry(
                            null,
                            index,
                            EntryStatus.PENDING,
                            "",
                            plan,
                            recipe,
                            null,
                            null);

                    List<Entry> entries = plan.getEntries();
                    entries.add(newEntry);
                    plan.setEntries(entries);

                    return planRepository.save(plan) // cascade saves entry
                            .getEntries()
                            .get(plan.getEntries().size() - 1);
                });
    }

}
