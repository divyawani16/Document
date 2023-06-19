package com.document_management.Service;
import com.document_management.DTO.DocumentAuditDto;
import com.document_management.DTO.DocumentVersionDto;
import com.document_management.Entity.Document;
import com.document_management.Entity.DocumentAudit;
import com.document_management.Entity.DocumentVersion;
import com.document_management.Repository.DocumentAuditRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class DocumentAuditService {

    @Autowired
    private final DocumentAuditRepository documentAuditRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public DocumentAuditService(DocumentAuditRepository documentAuditRepository) {
        this.documentAuditRepository = documentAuditRepository;
    }

    public DocumentAudit createDocumentAudit(DocumentAudit documentAudit) {
        return documentAuditRepository.save(documentAudit);
    }

public List<DocumentAuditDto> getAllDocAudit() {
    List<DocumentAudit> documentAudits = documentAuditRepository.findAll();
    return documentAudits.stream()
            .map(documentAudit -> modelMapper.map(documentAudit, DocumentAuditDto.class))
            .collect(Collectors.toList());
}
}
