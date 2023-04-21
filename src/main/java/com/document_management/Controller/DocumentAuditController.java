//package com.document_management.Controller;
//
//import com.document_management.DTO.DocumentAuditDto;
//import com.document_management.DTO.DocumentAuditRequestDto;
//import com.document_management.Service.DocumentAuditService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/document-audits")
//public class DocumentAuditController {
//
//    @Autowired
//    private DocumentAuditService documentAuditService;
//
//    @PostMapping
//    public ResponseEntity<DocumentAuditDto> createDocumentAudit(@RequestBody DocumentAuditRequestDto documentAuditRequestDto) {
//        DocumentAuditDto documentAuditDto = documentAuditService.createDocumentAudit(documentAuditRequestDto);
//        return ResponseEntity.ok(documentAuditDto);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<DocumentAuditDto> getDocumentAuditById(@PathVariable("id") Integer id) {
//        DocumentAuditDto documentAuditDto = documentAuditService.getDocumentAuditById(id);
//        return ResponseEntity.ok(documentAuditDto);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<DocumentAuditDto>> getAllDocumentAudits() {
//        List<DocumentAuditDto> documentAuditDtos = documentAuditService.getAllDocumentAudits();
//        return ResponseEntity.ok(documentAuditDtos);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<DocumentAuditDto> updateDocumentAudit(@PathVariable("id") Integer id, @RequestBody DocumentAuditRequestDto documentAuditRequestDto) {
//        DocumentAuditDto documentAuditDto = documentAuditService.updateDocumentAudit(id, documentAuditRequestDto);
//        return ResponseEntity.ok(documentAuditDto);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteDocumentAudit(@PathVariable("id") Integer id) {
//        documentAuditService.deleteDocumentAudit(id);
//        return ResponseEntity.ok("Document audit with id " + id + " has been deleted successfully.");
//    }
//}