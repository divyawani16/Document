package com.document_management.DTO;

import com.document_management.Entity.DocumentVersion;
import com.document_management.Entity.Property;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DocumentVersionMapper {

    private ModelMapper modelMapper = new ModelMapper();
    public DocumentVersionDto toDocumentVersionDto(DocumentVersion documentVersion) {
        return modelMapper.map(documentVersion, DocumentVersionDto.class);
    }

    public DocumentVersion toDocumentVersion (DocumentVersionDto documentVersionDto) {
        return modelMapper.map(documentVersionDto, DocumentVersion.class);
    }


}
