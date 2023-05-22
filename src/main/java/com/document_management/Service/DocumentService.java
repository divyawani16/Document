package com.document_management.Service;
import com.amazonaws.services.apigateway.model.NotFoundException;
import com.document_management.DTO.DocumentDetailsDto;
import com.document_management.DTO.DocumentDto;
import com.document_management.Entity.*;
import com.document_management.Repository.DocMimeTypeRepository;
import com.document_management.Repository.DocTypeRepository;
import com.document_management.Repository.DocumentRepository;
import com.document_management.Repository.PropertyRepository;
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

@Service
public class DocumentService {
    @Autowired
private DocTypeRepository docTypeRepository;
@Autowired
private PropertyRepository propertyRepository;
    @Autowired
private DocMimeTypeRepository docMimeTypeRepository;
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

    public String saveFile(MultipartFile file) throws IOException {
        String storagePath = "D://xyz"; // Specify the desired storage path here

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



    public DocumentDto getDocumentById(Integer documentId) {
        Document document = documentRepository.findById(documentId)
                .orElseThrow(() -> new RuntimeException("Document not found"));
        return modelMapper.map(document, DocumentDto.class);
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

//

//    public DocumentDto updateDocument(Integer documentId, DocumentDto documentDto) {
//        Document document = documentRepository.findById(documentId)
//                .orElseThrow(() -> new RuntimeException("Document not found"));
//        modelMapper.map(documentDto, document);
//        document = documentRepository.save(document);
//        return modelMapper.map(document, DocumentDto.class);
//    }
//
//    public void deleteDocument(Integer documentId) {
//        documentRepository.deleteById(documentId);
//    }

    // Add more methods as needed...
}

//
//    public DocumentDto getDocumentById(Integer documentId) {
//        Document document = documentRepository.findById(documentId).orElseThrow(() -> new RuntimeException("Document not found"));
//        return modelMapper.map(document, DocumentDto.class);
//    }
//
//    public DocumentDto createDocument(DocumentDto documentDto) {
//        Document document = modelMapper.map(documentDto, Document.class);
//        Document savedDocument = documentRepository.save(document);
//        return modelMapper.map(savedDocument, DocumentDto.class);
//    }
//    public DocumentDto updateDocument(Integer documentId, DocumentDto documentDto) {
//        Document document = documentRepository.findById(documentId).orElseThrow(() -> new RuntimeException("Document not found"));
//        modelMapper.map(documentDto, document);
//        Document updatedDocument = documentRepository.save(document);
//        return modelMapper.map(updatedDocument, DocumentDto.class);
//    }
//
//    public void deleteDocument(Integer documentId) {
//        documentRepository.deleteById(documentId);
//    }
//
////    public List<DocumentDto> getAllDocumentsByUserId(Integer userId) {
////        List<Document> documents = documentRepository.findByUserId(userId);
////        return documents.stream().map(d -> modelMapper.map(d, DocumentDto.class)).collect(Collectors.toList());
////    }
////
////    public List<DocumentDto> getAllDocumentsByPropertyId(Integer propertyId) {
////        List<Document> documents = documentRepository.findByPropertyId(propertyId);
////        return documents.stream().map(d -> modelMapper.map(d, DocumentDto.class)).collect(Collectors.toList());
////    }
//}
