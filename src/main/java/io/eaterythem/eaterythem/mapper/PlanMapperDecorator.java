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
    private final EntryMapper EntryMapper;
    private final PlanRepository PlanRepository;

    public PlanMapperDecorator(
            @Qualifier("planMapperImpl") PlanMapper delegate,
            EntryMapper EntryMapper,
            PlanRepository PlanRepository
    ) {
        this.delegate = delegate;
        this.EntryMapper = EntryMapper;
        this.PlanRepository = PlanRepository;
    }

    @Override
    public PlanDTO toDTO(Plan Plan) {
        PlanDTO dto = delegate.toDTO(Plan);
        dto.setBreakfastEntry(EntryMapper.toDTO(getOrCreateEntry(Plan, MealType.BREAKFAST)));
        dto.setLunchEntry(EntryMapper.toDTO(getOrCreateEntry(Plan, MealType.LUNCH)));
        dto.setDinnerEntry(EntryMapper.toDTO(getOrCreateEntry(Plan, MealType.DINNER)));
        dto.setEntries(EntryMapper.toDTO(Plan.getEntries()));
        return dto;
    }

    @Override
    public List<PlanDTO> toDTO(List<Plan> Plans) {
        return Plans.stream().map(this::toDTO).toList();
    }

    @Override
    public Plan toEntity(PlanDTO PlanDTO) {
        return delegate.toEntity(PlanDTO);
    }

    private Entry getOrCreateEntry(Plan Plan, MealType mealType) {
        return Plan.getEntries().stream()
                .filter(e -> e.getStatus() == EntryStatus.PENDING
                        && e.getPlannedRecipe().getMealType() == mealType)
                .max(Comparator.comparingInt(Entry::getDayIndex))
                .orElseGet(() -> {
                    int index;
                    var cycle = switch (mealType) {
                        case BREAKFAST -> {
                            index = Plan.getBreakfastIndex();
                            Plan.setBreakfastIndex(index + 1);
                            yield Plan.getBreakfastCycle();
                        }
                        case LUNCH -> {
                            index = Plan.getLunchIndex();
                            Plan.setLunchIndex(index + 1);
                            yield Plan.getLunchCycle();
                        }
                        case DINNER -> {
                            index = Plan.getDinnerIndex();
                            Plan.setDinnerIndex(index + 1);
                            yield Plan.getDinnerCycle();
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
                            Plan,
                            recipe,
                            null,
                            null);

                    List<Entry> entries = Plan.getEntries();
                    entries.add(newEntry);
                    Plan.setEntries(entries);

                    return PlanRepository.save(Plan) // cascade saves entry
                            .getEntries()
                            .get(Plan.getEntries().size() - 1);
                });
    }

}
