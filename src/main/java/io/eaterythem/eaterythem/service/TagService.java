package io.eaterythem.eaterythem.service;

import io.eaterythem.eaterythem.dto.TagDTO;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class TagService {
    public List<TagDTO> getAllTags() { return new ArrayList<>(); }
    public TagDTO getTagById(UUID id) { return null; }
    public TagDTO createTag(TagDTO tagDTO) { return null; }
    public TagDTO updateTag(UUID id, TagDTO tagDTO) { return null; }
    public void deleteTag(UUID id) {}
} 