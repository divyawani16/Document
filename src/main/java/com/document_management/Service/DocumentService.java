package com.document_management.Service;
import com.amazonaws.services.apigateway.model.NotFoundException;
import com.document_management.DTO.DocumentDetailsDto;
import com.document_management.DTO.DocumentDto;
import com.document_management.Entity.*;
import com.document_management.Repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import javax.persistence.EntityNotFoundException;

@Service
public class DocumentService {
    @Autowired
    private DocTypeRepository docTypeRepository;
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private DocMimeTypeRepository docMimeTypeRepository;

    @Autowired
    private UsersRepository usersRepository;
    private final DocumentRepository documentRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public DocumentService(DocumentRepository documentRepository, ModelMapper modelMapper) {
        this.documentRepository = documentRepository;
        this.modelMapper = modelMapper;
    }

    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    public void deleteDocument(Integer documentId) {
        documentRepository.deleteById(documentId);
    }

    public List<DocumentDetailsDto> getAllDocumentDetails() {
        List<Document> documents = documentRepository.findAll();
        List<DocumentDetailsDto> documentDetailsDtos = new ArrayList<>();

        for (Document document : documents) {
            DocumentDetailsDto documentDetailsDto = modelMapper.map(document, DocumentDetailsDto.class);
            documentDetailsDto.setUserName(document.getUser().getUsername());
            documentDetailsDto.setPropertyName(document.getProperty().getPropertyName());
            documentDetailsDtos.add(documentDetailsDto);
        }

        return documentDetailsDtos;
    }

    //    public Document createDocument(Document document) {
//        document.incrementDocumentVersion();
//        return documentRepository.save(document);
//    }
    public List<Document> searchDocumentsByPropertyName(String propertyName) {
        return documentRepository.findByPropertyPropertyName(propertyName);
    }

    public List<Document> searchDocumentsByUsername(String username) {
        return documentRepository.findByUserUsername(username);
    }
    public String saveFile(MultipartFile file) throws IOException {
        String storagePath = "C:\\Users\\bbdnet10237\\Desktop\\d"; // Specify the desired storage path here

        // Create the necessary directories if they don't exist
        File storageDir = new File(storagePath);
        if (!storageDir.exists()) {
            storageDir.mkdirs();
        }

        // Construct the file path by concatenating the storage path and the original file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String filePath = storagePath + File.separator + fileName;

        // Transfer the file to the target location
        File targetFile = new File(filePath);
        file.transferTo(targetFile);

        return filePath;
    }

    public DocumentDto updateDocumentApproval(int documentId, boolean approved) {
        Document document = documentRepository.findById(documentId)
                .orElseThrow(() -> new EntityNotFoundException("Document not found"));

        document.setApproved(approved);
        Document savedDocument = documentRepository.save(document);
        return mapDocumentToDocumentDto(savedDocument);
    }

    // Other existing methods...

    private DocumentDto mapDocumentToDocumentDto(Document document) {
        return new DocumentDto(document.getDocumentId(), document.getDocumentName(), document.isApproved());
    }

//    public String saveFile(MultipartFile file) throws IOException {
//        String storagePath = "D://xyz"; // Specify the desired storage path here
//
//        // Create the necessary directories if they don't exist
//        File storageDir = new File(storagePath);
//        if (!storageDir.exists()) {
//            storageDir.mkdirs();
//        }
//
//        // Construct the file path by concatenating the storage path and the original file name
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//        String filePath = storagePath + File.separator + fileName;
//
//        // Transfer the file to the target location
//        File targetFile = new File(filePath);
//        file.transferTo(targetFile);
//
//        return filePath;
//    }

    public DocMimeType getDocMimeTypeById(Integer docMimeTypeId) {
        return docMimeTypeRepository.findById(docMimeTypeId)
                .orElseThrow(() -> new NotFoundException("DocMimeType not found"));
    }

    public Property getPropertyById(Integer propertyId) {
        return propertyRepository.findById(propertyId)
                .orElseThrow(() -> new NotFoundException("Property not found"));
    }

    public DocType getDocTypeById(Integer docTypeId) {
        return docTypeRepository.findById(docTypeId)
                .orElseThrow(() -> new NotFoundException("DocType not found"));
    }

    public void addDocument(Document document) {
        documentRepository.save(document);
    }

    public int countDocument() {
        List<Document> documents = documentRepository.findAll();
        return documents.size();
    }

    public DocumentDto createDocument(DocumentDto documentDto) {
        Document document = modelMapper.map(documentDto, Document.class);
        document = documentRepository.save(document);
        return modelMapper.map(document, DocumentDto.class);
    }


    public Document getDocumentById(Integer documentId) {
        Document document = documentRepository.findById(documentId)
                .orElseThrow(() -> new RuntimeException("Document not found"));
        return modelMapper.map(document, Document.class);
    }
//    public DocumentDto getDocumentById(Integer documentId) {
//        Document document = documentRepository.findById(documentId)
//                .orElseThrow(() -> new RuntimeException("Document not found"));
//        return modelMapper.map(document, DocumentDto.class);
//    }

    public Optional<Property> getPropertyByName(String propertyName) {
        return propertyRepository.findByPropertyName(propertyName);
    }

    public Optional<DocType> getDocTypeByName(String docTypeName) {
        return docTypeRepository.findByDocTypeName(docTypeName);
    }

    public Optional<DocMimeType> getDocMimeTypeByName(String docMimeTypeName) {
        return docMimeTypeRepository.findByDocMimeTypeName(docMimeTypeName);
    }
    public Resource downloadDocument(Integer documentId) {
        Document document = documentRepository.findById(documentId)
                .orElseThrow(() -> new NotFoundException("Document not found"));

        File file = new File(document.getFilePath());
        if (!file.exists()) {
            throw new NotFoundException("File not found");
        }

        return new FileSystemResource(file);
    }

    public void updateDocument(Integer documentId, DocumentDto documentDto) {
        Document document = documentRepository.findById(documentId)
                .orElseThrow(() -> new NotFoundException("Document not found"));


        document.setDocumentName(documentDto.getDocumentName());


        if (documentDto.getUserId() != null) {
            Users user = usersRepository.findById(documentDto.getUserId())
                    .orElseThrow(() -> new NotFoundException("User not found"));
            document.setUser(user);
        }

        if (documentDto.getPropertyId() != null) {
            Property property = propertyRepository.findById(documentDto.getPropertyId())
                    .orElseThrow(() -> new NotFoundException("Property not found"));
            document.setProperty(property);
        }

        if (documentDto.getDocTypeId() != null) {
            DocType docType = docTypeRepository.findById(documentDto.getDocTypeId())
                    .orElseThrow(() -> new NotFoundException("DocType not found"));
            document.setDocType(docType);
        }

        if (documentDto.getDocMimeTypeId() != null) {
            DocMimeType docMimeType = docMimeTypeRepository.findById(documentDto.getDocMimeTypeId())
                    .orElseThrow(() -> new NotFoundException("DocMimeType not found"));
            document.setDocMimeType(docMimeType);
        }

        documentRepository.save(document);
    }

}