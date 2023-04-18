package com.document_management.Service;

import com.document_management.DTO.DocumentDto;
import com.document_management.DTO.RoleDto;
import com.document_management.Entity.Document;
import com.document_management.Entity.Role;
import com.document_management.Repository.DocumentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {
    private final DocumentRepository documentRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public DocumentService(DocumentRepository documentRepository, ModelMapper modelMapper) {
        this.documentRepository = documentRepository;
        this.modelMapper = modelMapper;
    }

    public DocumentDto getDocumentById(Integer documentId) {
        Document document = documentRepository.findById(documentId).orElseThrow(() -> new RuntimeException("Document not found"));
        return modelMapper.map(document, DocumentDto.class);
    }

    public DocumentDto createDocument(DocumentDto documentDto) {
        Document document = modelMapper.map(documentDto, Document.class);
        Document savedDocument = documentRepository.save(document);
        return modelMapper.map(savedDocument, DocumentDto.class);
    }
    public DocumentDto updateDocument(Integer documentId, DocumentDto documentDto) {
        Document document = documentRepository.findById(documentId).orElseThrow(() -> new RuntimeException("Document not found"));
        modelMapper.map(documentDto, document);
        Document updatedDocument = documentRepository.save(document);
        return modelMapper.map(updatedDocument, DocumentDto.class);
    }

    public void deleteDocument(Integer documentId) {
        documentRepository.deleteById(documentId);
    }

//    public List<DocumentDto> getAllDocumentsByUserId(Integer userId) {
//        List<Document> documents = documentRepository.findByUserId(userId);
//        return documents.stream().map(d -> modelMapper.map(d, DocumentDto.class)).collect(Collectors.toList());
//    }
//
//    public List<DocumentDto> getAllDocumentsByPropertyId(Integer propertyId) {
//        List<Document> documents = documentRepository.findByPropertyId(propertyId);
//        return documents.stream().map(d -> modelMapper.map(d, DocumentDto.class)).collect(Collectors.toList());
//    }
}
