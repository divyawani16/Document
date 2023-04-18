package com.document_management.DTO;

import com.document_management.Entity.DocumentTag;
import com.document_management.Entity.Property;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DocumentTagMapper {

    private ModelMapper modelMapper = new ModelMapper();

    public DocumentTagDto toDocumentTagDto(DocumentTag documentTag) {
        return modelMapper.map(documentTag, DocumentTagDto.class);
    }

    public DocumentTag toDocumentTag(DocumentTagDto documentTag) {
        return modelMapper.map(documentTag, DocumentTag.class);
    }
}
