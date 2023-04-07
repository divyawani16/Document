package com.document_management.Service;

import com.document_management.DTO.DocumentDto;
import com.document_management.DTO.DocumentMapper;
import com.document_management.Entity.Document;
import com.document_management.Repository.DocumentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private DocumentMapper documentMapper;

public List<DocumentDto> getAllDocuments() {
    List<Document> documents = documentRepository.findAll();
    return documents.stream()
            .map(document -> documentMapper.toDocumentDto(document))
            .collect(Collectors.toList());
}
      public DocumentDto getDocumentById(Long id) {
        Document document = documentRepository.findById(id).orElse(null);
        if (document == null) {
            return null;
        }
        return documentMapper.toDocumentDto(document);
    }

    public DocumentDto createDocument(DocumentDto documentDto) {
        Document document = documentMapper.toDocument(documentDto);
        Document savedDocument = documentRepository.save(document);
        return documentMapper.toDocumentDto(savedDocument);
    }




//    public DocumentDto updateDocument(Long id, DocumentDto documentDto) {
//        Document document = documentRepository.findById(id).orElse(null);
//        if (document == null) {
//            return null;
//        }
//        document.setName(documentDto.getName());
//        document.setUserId(documentDto.getUserId());
//        document.setPropertyId(documentDto.getPropertyId());
//        document.setDocTypeId(documentDto.getDocTypeId());
//        document.setDocMimeTypeId(documentDto.getDocMimeTypeId());
//        Document updatedDocument = documentRepository.save(document);
//        return documentMapper.toDocumentDto(updatedDocument);
//    }
}


