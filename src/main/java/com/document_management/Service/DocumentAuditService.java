package com.document_management.Service;

import com.document_management.DTO.DocumentAuditDto;
import com.document_management.Entity.DocumentAudit;
import com.document_management.Repository.DocumentAuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentAuditService {

    private final DocumentAuditRepository documentAuditRepository;

    @Autowired
    public DocumentAuditService(DocumentAuditRepository documentAuditRepository) {
        this.documentAuditRepository = documentAuditRepository;
    }

    public DocumentAudit createDocumentAudit(DocumentAudit documentAudit) {
        return documentAuditRepository.save(documentAudit);
    }

//    public DocumentAuditDto getDocumentAuditById(Integer documentAuditId) {
//        DocumentAudit documentAudit = documentAuditRepository.findById(documentAuditId)
//                .orElseThrow(() -> new RuntimeException("Document audit not found with id: " + documentAuditId));
//        return new DocumentAuditDto(documentAudit.getId(), documentAudit.getDocumentId(), documentAudit.getAction(), documentAudit.getTimestamp());
//    }
}
