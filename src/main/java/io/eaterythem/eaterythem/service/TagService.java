package io.eaterythem.eaterythem.service;

import io.eaterythem.eaterythem.dto.TagDTO;
import io.eaterythem.eaterythem.mapper.TagMapper;
import io.eaterythem.eaterythem.model.Tag;
import io.eaterythem.eaterythem.repository.TagRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import java.util.*;

@AllArgsConstructor
@Service
public class TagService {
    TagRepository tagRepository;
    TagMapper tagMapper;

    public List<TagDTO> getAllTags() { 
        List<Tag> tags = tagRepository.findAll();
        return tagMapper.toDTO(tags);
    }
    public TagDTO getTagById(Integer id) { return null; }
    public TagDTO createTag(TagDTO tagDTO) { return null; }
    public TagDTO updateTag(Integer id, TagDTO tagDTO) { return null; }
    public void deleteTag(Integer id) {}
} 