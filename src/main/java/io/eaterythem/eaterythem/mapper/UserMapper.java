package io.eaterythem.eaterythem.mapper;

import io.eaterythem.eaterythem.dto.MealPlanDTO;
import io.eaterythem.eaterythem.dto.UserDTO;
import io.eaterythem.eaterythem.model.MealPlan;
import io.eaterythem.eaterythem.model.MealPlanParticipant;
import io.eaterythem.eaterythem.model.User;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = { RecipeMapper.class, MealPlanMapper.class,
        MealCycleMapper.class }, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User toEntity(UserDTO userDTO);

    @Mapping(target = "ownedCycles", source = "mealCycles")
    @Mapping(target = "mealPlans", expression = "java(mapMealPlans(user.getMealPlanParticipants()))")
    UserDTO toDTO(User user);

    default List<MealPlanDTO> mapMealPlans(List<MealPlanParticipant> participants) {
        if (participants == null)
            return null;
        return participants.stream()
                .map(MealPlanParticipant::getMealPlan)
                .map(this::toMealPlanDTO)
                .toList();
    }

    MealPlanDTO toMealPlanDTO(MealPlan mealPlan);
}