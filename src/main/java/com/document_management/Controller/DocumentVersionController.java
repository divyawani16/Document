package com.document_management.Controller;

import com.document_management.DTO.DocumentVersionDto;
import com.document_management.Service.DocumentVersionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/documentVersions")
@AllArgsConstructor
public class DocumentVersionController {

    private final DocumentVersionService documentVersionService;

    @GetMapping("/{docVersionId}")
    public ResponseEntity<DocumentVersionDto> getDocumentVersionById(@PathVariable int docVersionId) {
        DocumentVersionDto documentVersionDto = documentVersionService.getDocumentVersionById(docVersionId);
        return ResponseEntity.ok().body(documentVersionDto);
    }

    @PostMapping
    public ResponseEntity<DocumentVersionDto> createDocumentVersion(@RequestBody DocumentVersionDto documentVersionDto) {
        DocumentVersionDto createdDocumentVersionDto = documentVersionService.createDocumentVersion(documentVersionDto);
        return ResponseEntity.created(URI.create("/api/documentVersions/" + createdDocumentVersionDto.getDocumentVersionId())).body(createdDocumentVersionDto);
    }
}
