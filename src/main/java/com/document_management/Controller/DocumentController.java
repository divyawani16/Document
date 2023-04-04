package com.document_management.Controller;
import com.document_management.Entity.Document;
import com.document_management.Service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {
    @Autowired
    private DocumentService documentService;

    @GetMapping("/get")
    public List<Document> getAllDocuments() {
        return documentService.getAllDocuments();
    }

    @GetMapping("/{id}")
    public Document getDocumentById(@PathVariable Long id) {
        return documentService.getDocumentById(id);
    }



    @PostMapping("/add")
    public Document createDocument(@Valid @RequestBody Document document) {
        return documentService.createDocument(document);
    }

    @PutMapping("/{id}")
    public Document updateDocument(@PathVariable Long id, @Valid @RequestBody Document documentDetails) {
        return documentService.updateDocument(id, documentDetails);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteDocument(@PathVariable Long id) {
//        documentService.deleteDocument(id);
//        return ResponseEntity.ok().build();
//    }
}
