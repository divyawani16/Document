//package com.hello.hello.Service;
//
//        import com.hello.hello.Entity.Tag;
//        import com.hello.hello.Repository.TagRepository;
//        import org.springframework.beans.factory.annotation.Autowired;
//        import org.springframework.stereotype.Service;
//
//        import java.util.List;
//
//@Service
//public class TagService {
//    @Autowired
//    private TagRepository tagRepository;
//
//    public List<Tag> getAllTags() {
//        return tagRepository.findAll();
//    }
//
//    public Tag getTagById(Long id) {
//        return tagRepository.findById(id).orElse(null);
//    }
//
//    public Tag createTag(Tag tag) {
//        return tagRepository.save(tag);
//    }
//
//    public void deleteTag(Long id) {
//        tagRepository.deleteById(id);
//    }
//}
