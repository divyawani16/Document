package com.document_management.DTO;

import com.document_management.Entity.Document;
import com.document_management.Entity.DocumentAudit;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DocumentAuditMapper {
    private ModelMapper modelMapper = new ModelMapper();
    public DocumentAuditDto toDocumentAuditDto(DocumentAudit documentAudit) {
        return modelMapper.map(documentAudit, DocumentAuditDto.class);
    }

    public DocumentAudit toDocumentAudit(DocumentAuditDto documentAuditDto) {
        return modelMapper.map(documentAuditDto, DocumentAudit.class);
    }
}
