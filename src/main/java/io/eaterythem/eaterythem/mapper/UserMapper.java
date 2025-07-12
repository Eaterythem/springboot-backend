package io.eaterythem.eaterythem.mapper;

import io.eaterythem.eaterythem.dto.UserDTO;
import io.eaterythem.eaterythem.model.User;
import org.mapstruct.Mapper;
// import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {RecipeMapper.class, MealPlanMapper.class, MealCycleMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserDTO toDTO(User user);

    User toEntity(UserDTO userDTO);
} 