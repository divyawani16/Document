package com.document_management.Controller;
import com.document_management.Entity.DocumentTag;
import com.document_management.Service.DocumentTagService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/document-tags")
public class DocumentTagController {
    private final DocumentTagService documentTagService;
    private static final Logger logger = LogManager.getLogger(DocumentTagController.class);
    @GetMapping("/")
    public ResponseEntity<List<DocumentTag>> getAllDocumentTags() {

        return ResponseEntity.ok(documentTagService.getAllDocumentTags());
    }

    @PostMapping("/")
    public ResponseEntity<DocumentTag> createDocumentTag(@Valid @RequestBody DocumentTag documentTag) {
        return ResponseEntity.status(HttpStatus.CREATED).body(documentTagService.createDocumentTag(documentTag));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentTag> getDocumentTagById(@PathVariable Long id) {
        return ResponseEntity.ok(documentTagService.getDocumentTagById(id));
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteDocumentTag(@PathVariable Long id) {
//        documentTagService.deleteDocumentTag(id);
//        return ResponseEntity.noContent().build();
//    }
}
