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
import com.document_management.Repository.DocMimeTypeRepository;
import com.document_management.Repository.DocTypeRepository;
import com.document_management.Repository.DocumentRepository;
import com.document_management.Repository.PropertyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
    private final ModelMapper modelMapper;
    private final AWSS3Service awsS3Service;

    @Autowired
    public DocumentService(DocumentRepository documentRepository, ModelMapper modelMapper,
                           AWSS3Service awsS3Service) {
        this.documentRepository = documentRepository;
        this.modelMapper = modelMapper;
        this.awsS3Service = awsS3Service;

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
        // Upload file to AWS S3 and get the public URL
        String publicURL = awsS3Service.uploadFile(file);

        return publicURL;
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
//    public Resource downloadDocument(Integer documentId) {
//        Document document = documentRepository.findById(documentId)
//                .orElseThrow(() -> new NotFoundException("Document not found"));
//
//        File file = new File(document.getFilePath());
//        if (!file.exists()) {
//            throw new NotFoundException("File not found");
//        }
//
//        return new FileSystemResource(file);
//    }
}