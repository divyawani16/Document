package com.document_management.Controller;
import com.document_management.DTO.DocumentAuditDto;
import com.document_management.Entity.DocumentAudit;
import com.document_management.Entity.DocumentVersion;
import com.document_management.Entity.Stage;
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

    @Autowired
    private DocumentAuditRepository documentAuditRepository;
    private final DocumentAuditService documentAuditService;
    private final ModelMapper modelMapper;

    @Autowired
    public DocumentAuditController(DocumentAuditService documentAuditService, ModelMapper modelMapper) {
        this.documentAuditService = documentAuditService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/audit")
    public ResponseEntity<DocumentAuditDto> createDocumentAudit(@RequestBody DocumentAuditDto documentAuditDto) {
        DocumentAudit documentAudit = convertToEntity(documentAuditDto);
        documentAudit = documentAuditService.createDocumentAudit(documentAudit);
        DocumentAuditDto createdDocumentAuditDto = convertToDto(documentAudit);
        return new ResponseEntity<>(createdDocumentAuditDto, HttpStatus.CREATED);
    }


    @GetMapping("/")
    public List<DocumentAuditDto> getAllDocAudit() {
        List<DocumentAudit> documentAudits = documentAuditRepository.findAll();
        return documentAudits.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private DocumentAuditDto convertToDto(DocumentAudit documentAudit) {
        DocumentAuditDto documentAuditDto = modelMapper.map(documentAudit, DocumentAuditDto.class);
        Stage stage = documentAudit.getStage();
        if (stage != null) {
            documentAuditDto.setStageName(stage.getStageName());
        } else {
            documentAuditDto.setStageName("Unknown");
        }
        documentAuditDto.setDocumentName(documentAudit.getDocumentVersion().getDocumentId().getDocumentName());
        return documentAuditDto;
    }

    private DocumentAudit convertToEntity(DocumentAuditDto documentAuditDto) {
        DocumentAudit documentAudit = modelMapper.map(documentAuditDto, DocumentAudit.class);
        DocumentVersion documentVersion = new DocumentVersion();
        documentVersion.setDocumentVersionId(documentAuditDto.getDocumentVersionId());
        documentAudit.setDocumentVersion(documentVersion);
        return documentAudit;
    }


}

