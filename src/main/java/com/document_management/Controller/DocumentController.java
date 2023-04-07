package com.document_management.Controller;
import com.document_management.DTO.DocumentDto;
import com.document_management.Entity.Document;
import com.document_management.Service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/documents")
public class DocumentController {
    @Autowired
    private DocumentService documentService;

@GetMapping("/")
public List<DocumentDto> getAllDocuments() {
    return documentService.getAllDocuments();
}
    @GetMapping("/{id}")
    public DocumentDto getDocumentById(@PathVariable Long id) {
        return documentService.getDocumentById(id);
    }
    @PostMapping("/")
    public DocumentDto createDocument(@Valid @RequestBody DocumentDto documentDto) {
        return documentService.createDocument(documentDto);
    }







//
//    @PostMapping("/")
//    public Document createDocument(@Valid @RequestBody Document document) {
//        return documentService.createDocument(document);
//    }
//@GetMapping("/")
//public List<DocumentDto> getAllDocuments() {
//    return documentService.getAllDocuments().stream()
//            .map(document -> documentService.toDocumentDto(document))
//            .collect(Collectors.toList());
//}


//    @GetMapping("/{id}")
//    public DocumentDto getDocumentById(@PathVariable Long id) {
//        return documentService(documentService.getDocumentById(id));
//    }

//    @PostMapping("/")
//    public DocumentDto createDocument(@Valid @RequestBody DocumentDto documentDto) {
//        return documentService.toDocumentDto(documentService.createDocument(documentService.toDocumentDto(documentDto)));
//    }

//    @PostMapping("/")
//    public ResponseEntity<DocumentDto> createDocument(@Valid @RequestBody DocumentDto documentDto) {
//        Document document = documentService.createDocument(documentService.toDocument(documentDto));
//        DocumentDto createdDocumentDto = documentService.toDocumentDto(document);
//        return ResponseEntity.ok(createdDocumentDto);
//    }

//    @PutMapping("/{id}")
//    public Document updateDocument(@PathVariable Long id, @Valid @RequestBody Document documentDetails) {
//        return documentService.updateDocument(id, documentDetails);
//    }

//    @PutMapping("/{id}")
//    public DocumentDto updateDocument(@PathVariable Long id, @Valid @RequestBody DocumentDto documentDto) {
//        return documentService.updateDocument(id, documentDto);
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteDocument(@PathVariable Long id) {
//        documentService.deleteDocument(id);
//        return ResponseEntity.ok().build();
//    }
}
