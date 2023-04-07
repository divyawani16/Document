package com.document_management.DTO;
import com.document_management.Entity.Document;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class DocumentMapper {
    private ModelMapper modelMapper = new ModelMapper();

    public DocumentDto toDocumentDto(Document document) {
        return modelMapper.map(document, DocumentDto.class);
    }

    public Document toDocument(DocumentDto documentDto) {
        return modelMapper.map(documentDto, Document.class);
    }
}
