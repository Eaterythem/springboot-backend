package io.eaterythem.eaterythem.mapper;

import io.eaterythem.eaterythem.dto.TagDTO;
import io.eaterythem.eaterythem.model.Tag;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {RecipeMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagMapper {

    TagDTO toDTO(Tag tag);

    Tag toEntity(TagDTO tagDTO);

    List<TagDTO> toDTO(List<Tag> tags);
} 