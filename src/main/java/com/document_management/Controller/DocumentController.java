package com.document_management.Controller;
import com.document_management.DTO.DocumentDto;
import com.document_management.Service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {
    private DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/{documentId}")
    public ResponseEntity<DocumentDto> getDocumentById(@PathVariable Integer documentId) {
        DocumentDto documentDto = documentService.getDocumentById(documentId);
        return ResponseEntity.ok(documentDto);
    }

    @PostMapping("/")
    public ResponseEntity<DocumentDto> createDocument(@RequestBody DocumentDto documentDto) {
        DocumentDto createdDocumentDto = documentService.createDocument(documentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDocumentDto);
    }

    @PutMapping("/{documentId}")
    public ResponseEntity<DocumentDto> updateDocument(@PathVariable Integer documentId, @RequestBody DocumentDto documentDto) {
            DocumentDto updatedDocumentDto = documentService.updateDocument(documentId, documentDto);
        return ResponseEntity.ok(updatedDocumentDto);
    }

    @DeleteMapping("/{documentId}")
    public ResponseEntity<Void> deleteDocument(@PathVariable Integer documentId) {
        documentService.deleteDocument(documentId);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/user/{userId}")
//    public ResponseEntity<List<DocumentDto>> getAllDocumentsByUserId(@PathVariable Integer userId) {
//        List<DocumentDto> documentDtoList = documentService.getAllDocumentsByUserId(userId);
//        return ResponseEntity.ok(documentDtoList);
//    }
//
//    @GetMapping("/property/{propertyId}")
//    public ResponseEntity<List<DocumentDto>> getAllDocumentsByPropertyId(@PathVariable Integer propertyId) {
//        List<DocumentDto> documentDtoList = documentService.getAllDocumentsByPropertyId(propertyId);
//        return ResponseEntity.ok(documentDtoList);
//    }
}
