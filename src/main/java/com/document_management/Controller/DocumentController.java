package com.document_management.Controller;
import com.document_management.DTO.DocumentDetailsDto;
import com.document_management.DTO.DocumentDto;
import com.document_management.Entity.*;
import com.document_management.Repository.*;
import com.document_management.Service.DocumentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;

@RestController
@RequestMapping("api/documents")
@CrossOrigin("*")
public class DocumentController {
    private final DocumentService documentService;
    private UsersRepository usersRepository;
    private PropertyRepository propertyRepository;
    private ModelMapper modelMapper;
    private final DocumentRepository documentRepository;
    private DocTypeRepository docTypeRepository;
    private DocMimeTypeRepository docMimeTypeRepository;
    @Autowired
    public DocumentController(DocumentService documentService, UsersRepository usersRepository, ModelMapper modelMapper,
                              DocumentRepository documentRepository, PropertyRepository propertyRepository, DocMimeTypeRepository docMimeTypeRepository,DocTypeRepository docTypeRepository) {
        this.documentService = documentService;
        this.usersRepository = usersRepository;
        this.modelMapper = modelMapper;
        this.documentRepository = documentRepository;
        this.propertyRepository = propertyRepository;
        this.docTypeRepository = docTypeRepository;
        this.docMimeTypeRepository = docMimeTypeRepository;
    }
    @GetMapping("/count")
    public ResponseEntity<Integer> countDocument() {
        int count = documentService.getAllDocuments().size();
        return ResponseEntity.ok(count);
    }
    @GetMapping("/getalldocumentsdetails")
    public List<DocumentDetailsDto> getAllDocumentsWithDetails() {
        List<Document> documents = documentRepository.findAll();
        List<DocumentDetailsDto> documentDetails = new ArrayList<>();
        for (Document document : documents) {
            DocumentDetailsDto details = new DocumentDetailsDto();
            details.setDocumentName(document.getDocumentName());
            Users user = document.getUser();
            if (user != null) {
                details.setUserName(user.getUsername());
            }
            Property property = document.getProperty();
            if (property != null) {
                details.setPropertyName(property.getPropertyName());
            }
            DocType docType=document.getDocType();
            if(docType !=null){
                details.setDocTypeName(docType.getDocTypeName());
            }
            DocMimeType docMimeType=document.getDocMimeType();
            if(docMimeType !=null){
                details.setDocMimeTypeName(docMimeType.getDocMimeTypeName());
            }
            documentDetails.add(details);
        }
        return documentDetails;
    }
    @GetMapping("/documents/propertyname")
    public List<Document> searchDocumentsByPropertyName(@RequestParam("propertyName") String propertyName) {
        return documentRepository.findByPropertyPropertyName(propertyName);
    }
    @GetMapping("/documents/username")
    public List<Document> searchDocumentsByUsername(@RequestParam("username") String username) {
        return documentService.searchDocumentsByUsername(username);
    }
    @PostMapping
    public ResponseEntity<String> addDocument(
            @RequestParam("file") MultipartFile file,
            @RequestParam("documentName") String documentName,
            @RequestParam("username") String username,
            @RequestParam("propertyName") String propertyName,
            @RequestParam("docTypeName") String docTypeName,
            @RequestParam("docMimeTypeName") String docMimeTypeName
    ) {
        try {
            Document document = new Document();
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

            String storagePath = documentService.saveFile(file);
            document.setFilePath(storagePath);

            documentService.addDocument(document);

            return ResponseEntity.ok("Document added successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add document.");
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable Integer id) {
        documentService.deleteDocument(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/get")
    public ResponseEntity<List<DocumentDto>> getAllDocuments() {
        List<Document> documents = documentService.getAllDocuments();
        List<DocumentDto> documentDtos = documents.stream()
                .map(document -> modelMapper.map(document, DocumentDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(documentDtos);
    }
    @GetMapping("/{documentId}")
    public ResponseEntity<Document> getDocumentById(@PathVariable Integer documentId) {
        Document document = documentService.getDocumentById(documentId);
        return ResponseEntity.ok(document);
    }

    @GetMapping("/{documentId}/download")
    public ResponseEntity<Resource> downloadDocument(@PathVariable Integer documentId) throws IOException {
        Resource fileResource = documentService.downloadDocument(documentId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileResource.getFilename() + "\"")
                .body(fileResource);
    }
}
