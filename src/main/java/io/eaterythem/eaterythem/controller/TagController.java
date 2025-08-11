package io.eaterythem.eaterythem.controller;

import io.eaterythem.eaterythem.dto.TagDTO;
import io.eaterythem.eaterythem.service.TagService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {
    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public List<TagDTO> getAllTags() {
        return tagService.getAllTags();
    }

    @GetMapping("/{id}")
    public TagDTO getTagById(@PathVariable Integer id) {
        return tagService.getTagById(id);
    }

    @PostMapping
    public TagDTO createTag(@RequestBody TagDTO tagDTO) {
        return tagService.createTag(tagDTO);
    }

    @PutMapping("/{id}")
    public TagDTO updateTag(@PathVariable Integer id, @RequestBody TagDTO tagDTO) {
        return tagService.updateTag(id, tagDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteTag(@PathVariable Integer id) {
        tagService.deleteTag(id);
    }
} 