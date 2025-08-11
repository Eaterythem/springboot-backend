package io.eaterythem.eaterythem.service;

import io.eaterythem.eaterythem.dto.TagDTO;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class TagService {
    public List<TagDTO> getAllTags() { return new ArrayList<>(); }
    public TagDTO getTagById(Integer id) { return null; }
    public TagDTO createTag(TagDTO tagDTO) { return null; }
    public TagDTO updateTag(Integer id, TagDTO tagDTO) { return null; }
    public void deleteTag(Integer id) {}
} 