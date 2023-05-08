package com.document_management.Controller;

import com.document_management.DTO.DocumentAuditDto;
import com.document_management.Entity.DocumentAudit;
import com.document_management.Repository.DocumentAuditRepository;
import com.document_management.Service.DocumentAuditService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/document-audit")
public class DocumentAuditController {

    private final DocumentAuditService documentAuditService;
    private ModelMapper modelMapper;

    @Autowired
    public DocumentAuditController(DocumentAuditService documentAuditService, ModelMapper modelMapper) {
        this.documentAuditService = documentAuditService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/audit")
    public ResponseEntity<DocumentAuditDto> createDocumentAudit(@RequestBody DocumentAuditDto documentAuditDto) {
        DocumentAudit documentAudit = modelMapper.map(documentAuditDto, DocumentAudit.class);
        documentAudit = documentAuditService.createDocumentAudit(documentAudit);
        DocumentAuditDto createdDocumentAuditDto = modelMapper.map(documentAudit, DocumentAuditDto.class);
        return new ResponseEntity<>(createdDocumentAuditDto, HttpStatus.CREATED);
    }


//    @GetMapping("/{documentAuditId}")
//    public ResponseEntity<DocumentAuditDto> getDocumentAuditById(@PathVariable Integer documentAuditId) {
//        DocumentAuditDto documentAudit = documentAuditService.getDocumentAuditById(documentAuditId);
//        return ResponseEntity.ok(documentAudit);
//    }
}
