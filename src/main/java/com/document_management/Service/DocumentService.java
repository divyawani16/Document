package com.document_management.Service;
//import com.amazonaws.services.apigateway.model.NotFoundException;
import com.amazonaws.services.kms.model.NotFoundException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.document_management.AWSS3Service;
import com.document_management.DTO.DocumentDetailsDto;
import com.document_management.DTO.DocumentDto;
import com.document_management.Entity.*;
import com.document_management.Repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.web.server.ResponseStatusException;


@Service
public class DocumentService {
    @Autowired
    private DocTypeRepository docTypeRepository;
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private DocMimeTypeRepository docMimeTypeRepository;
    private final DocumentRepository documentRepository;
@Autowired
private UsersRepository usersRepository;
    @Autowired
    private final ModelMapper modelMapper;
    private final AWSS3Service awsS3Service;

    @Autowired
    public DocumentService(DocumentRepository documentRepository, ModelMapper modelMapper,
                           AWSS3Service awsS3Service) {
        this.documentRepository = documentRepository;
        this.modelMapper = modelMapper;
        this.awsS3Service = awsS3Service;
    }
    public ResponseEntity<Resource> downloadDocument(Integer documentId) throws IOException {
        Document document = documentRepository.findById(documentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Document not found"));

        AmazonS3 s3Client = awsS3Service.getAmazonS3Client();
        S3Object s3Object = s3Client.getObject("documentsmanagement", document.getFilePath());
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        InputStreamResource inputStreamResource = new InputStreamResource(inputStream);


        String filename = extractFilename(document.getFilePath());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", filename);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return ResponseEntity.ok()
                .headers(headers)
                .body(inputStreamResource);
    }

    private String extractFilename(String filePath) {
        String[] pathParts = filePath.split("/");
        return pathParts[pathParts.length - 1];
    }

    public ResponseEntity<String> updateDocument(
            Integer documentId,
            MultipartFile file,
            String documentName,
            String username,
            String propertyName,
            String docTypeName,
            String docMimeTypeName
    ) throws IOException {
        Document document = getDocumentById(documentId);

        if (document == null) {
            return ResponseEntity.notFound().build();
        }

        document.setDocumentName(documentName);

        Users user = usersRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Invalid username"));
        document.setUser(user);

        Property property = propertyRepository.findByPropertyName(propertyName)
                .orElseThrow(() -> new IllegalArgumentException("Invalid propertyName"));
        document.setProperty(property);

        DocType docType = docTypeRepository.findByDocTypeName(docTypeName)
                .orElseThrow(() -> new IllegalArgumentException("Invalid docTypeName"));
        document.setDocType(docType);

        Optional<DocMimeType> optionalDocMimeType = docMimeTypeRepository.findByDocMimeTypeName(docMimeTypeName);
        DocMimeType docMimeType = optionalDocMimeType.orElseThrow(() -> new IllegalArgumentException("Invalid docMimeTypeName"));
        document.setDocMimeType(docMimeType);

        String publicURL = awsS3Service.uploadFile(file);
        document.setFilePath(publicURL);

        addDocument(document);

        return ResponseEntity.ok("Document updated successfully.");
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
    public String saveFile(MultipartFile file) throws IOException {
        String publicURL = awsS3Service.uploadFile(file);

        return publicURL;
    }
//    public List<Document> searchDocumentsByPropertyName(String propertyName) {
//        return documentRepository.findByPropertyPropertyName(propertyName);
//    }
public List<Document> searchDocumentsByPropertyName(String propertyName) {
    List<Document> documents = documentRepository.findByPropertyPropertyName(propertyName);
    List<Document> smartworksDocuments = new ArrayList<>();

    for (Document document : documents) {
        if (document.getProperty().getPropertyName().equals("Smartworks")) {
            smartworksDocuments.add(document);
        }
    }

    return smartworksDocuments;
}

    public List<Document> searchDocumentsByUsername(String username) {
        return documentRepository.findByUserUsername(username);
    }
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

    public Optional<Property> getPropertyByName(String propertyName) {
        return propertyRepository.findByPropertyName(propertyName);
    }

    public Optional<DocType> getDocTypeByName(String docTypeName) {
        return docTypeRepository.findByDocTypeName(docTypeName);
    }

    public Optional<DocMimeType> getDocMimeTypeByName(String docMimeTypeName) {
        return docMimeTypeRepository.findByDocMimeTypeName(docMimeTypeName);
    }

}