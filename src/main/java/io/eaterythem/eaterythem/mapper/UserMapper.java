package io.eaterythem.eaterythem.mapper;

import io.eaterythem.eaterythem.dto.UserDTO;
import io.eaterythem.eaterythem.model.User;
import org.mapstruct.Mapper;
// import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {RecipeMapper.class, MealPlanMapper.class, MealCycleMapper.class})
public interface UserMapper {

    UserDTO toDTO(User user);

    // @Mapping(target = "password", ignore = true)
    User toEntity(UserDTO userDTO);
} 