package io.eaterythem.eaterythem.mapper;

import io.eaterythem.eaterythem.dto.TagDTO;
import io.eaterythem.eaterythem.model.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {RecipeMapper.class})
public interface TagMapper {
    TagDTO toDTO(Tag tag);

    @Mapping(target = "recipes", ignore = true)
    Tag toEntity(TagDTO tagDTO);
} 