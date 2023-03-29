//package com.hello.hello.Controller;
//
//        import com.hello.hello.Entity.Tag;
//        import com.hello.hello.Service.TagService;
//        import org.springframework.beans.factory.annotation.Autowired;
//        import org.springframework.http.ResponseEntity;
//        import org.springframework.web.bind.annotation.*;
//
//        import java.util.List;
//
//@RestController
//@RequestMapping("/api/tags")
//public class TagController {
//    @Autowired
//    private TagService tagService;
//
//    @GetMapping("/get")
//    public List<Tag> getAllTags() {
//        return tagService.getAllTags();
//    }
//
//    @GetMapping("/{id}")
//    public Tag getTagById(@PathVariable Long id) {
//        return tagService.getTagById(id);
//    }
//
//    @PostMapping("/add")
//    public Tag createTag(@RequestBody Tag tag) {
//        return tagService.createTag(tag);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteTag(@PathVariable Long id) {
//        tagService.deleteTag(id);
//        return ResponseEntity.noContent().build();
//    }
//}