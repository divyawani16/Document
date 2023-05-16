package com.document_management.Controller;

import com.document_management.DTO.DocMimeTypeDto;
import com.document_management.DTO.DocumentAuditDto;
import com.document_management.DTO.DocumentDto;
import com.document_management.DTO.DocumentVersionDto;
import com.document_management.Entity.Document;
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
@CrossOrigin(origins = "http://localhost:4200")
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
//    @GetMapping("")
//    public ResponseEntity<List<DocumentAuditDto>> getAllDocAudit() {
//        List<DocumentAuditDto> documentAuditDtos = documentAuditService.getAllDocAudit();
//        return ResponseEntity.ok(documentAuditDtos);
//    }
//    @GetMapping("/get")
//    public ResponseEntity<List<DocumentAuditDto>> getAllDocAudit() {
//        List<DocumentAudit> documentAudits = documentAuditService.getAllDocAudit();
//        List<DocumentAuditDto> documentAuditDtos = documentAudits.stream()
//                .map(document -> modelMapper.map(documentAudits, DocumentAuditDto.class))
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(documentAuditDtos);}
        @GetMapping("/get")
        public ResponseEntity<List<DocumentAuditDto>> getAllDocAudit() {
            List<DocumentAuditDto> documentVersionDtos = documentAuditService.getAllDocAudit();
            return ResponseEntity.ok(documentVersionDtos);
        }

//    @GetMapping("/{documentAuditId}")
//    public ResponseEntity<DocumentAuditDto> getDocumentAuditById(@PathVariable Integer documentAuditId) {
//        DocumentAuditDto documentAudit = documentAuditService.getDocumentAuditById(documentAuditId);
//        return ResponseEntity.ok(documentAudit);
//    }
}
