package io.eaterythem.eaterythem.mapper;

import io.eaterythem.eaterythem.dto.TagDTO;
import io.eaterythem.eaterythem.model.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {RecipeMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagMapper {

    TagDTO toDTO(Tag tag);

    // @Mapping(target = "recipes", ignore = true)
    // @Mapping(target = "id", ignore = true)
    Tag toEntity(TagDTO tagDTO);
} 