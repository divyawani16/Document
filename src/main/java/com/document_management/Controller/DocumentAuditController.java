package com.document_management.Controller;

        import com.document_management.Entity.DocumentAudit;
        import com.document_management.Service.DocumentAuditService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;
        import java.util.List;

@RestController
@RequestMapping("/api/document-audits")
public class DocumentAuditController {

    @Autowired
    private DocumentAuditService documentAuditService;

    @GetMapping("/")
    public ResponseEntity<List<DocumentAudit>> getAllDocumentAudits() {
        List<DocumentAudit> documentAudits = documentAuditService.getAllDocumentAudits();
        return ResponseEntity.ok(documentAudits);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DocumentAudit> getDocumentAuditById(@PathVariable Long id) {
        DocumentAudit documentAudit = documentAuditService.getDocumentAuditById(id);
        return ResponseEntity.ok(documentAudit);
    }
    @PostMapping("/")
    public ResponseEntity<DocumentAudit> saveDocumentAudit(@RequestBody DocumentAudit documentAudit) {
        DocumentAudit savedDocumentAudit = documentAuditService.saveDocumentAudit(documentAudit);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDocumentAudit);
    }
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteDocumentAuditById(@PathVariable Long id) {
//        documentAuditService.deleteDocumentAuditById(id);
//        return ResponseEntity.noContent().build();
//    }

}

