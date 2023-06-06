package com.document_management.Controller;
import com.document_management.Entity.Tag;
import com.document_management.Service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public List<Tag> getAllTags() {
        return tagService.getAllTags();
    }

    @GetMapping("/{id}")
    public Tag getTagById(@PathVariable Long id) {
        return tagService.getTagById(id);
    }

    @PostMapping("/")
    public Tag createTag(@RequestBody Tag tag) {
        return tagService.createTag(tag);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteTag(@PathVariable Long id) {
//        tagService.deleteTag(id);
//        return ResponseEntity.noContent().build();
//    }
}