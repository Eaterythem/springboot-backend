package io.eaterythem.eaterythem.mapper;

import io.eaterythem.eaterythem.dto.TagDTO;
import io.eaterythem.eaterythem.model.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TagMapper {
    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);
    TagDTO toDTO(Tag tag);
    Tag toEntity(TagDTO tagDTO);
} 