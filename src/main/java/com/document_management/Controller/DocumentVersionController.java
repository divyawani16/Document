package com.document_management.Controller;
import com.document_management.Entity.DocumentVersion;
import com.document_management.Service.DocumentVersionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/documentVersions")
public class DocumentVersionController {
    private final DocumentVersionService service;

    public DocumentVersionController(DocumentVersionService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public ResponseEntity<DocumentVersion> create(@RequestBody DocumentVersion documentVersion) {
        DocumentVersion savedDocumentVersion = service.save(documentVersion);
        return ResponseEntity
                .created(URI.create("/documentVersions/" + savedDocumentVersion.getDocumentVersionId()))
                .body(savedDocumentVersion);
    }

    @GetMapping
    public ResponseEntity<List<DocumentVersion>> getAll() {
        List<DocumentVersion> documentVersions = service.getAll();
        return ResponseEntity.ok(documentVersions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentVersion> getById(@PathVariable int id) {
        DocumentVersion documentVersion = service.getById(id);
        return ResponseEntity.ok(documentVersion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
