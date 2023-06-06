package com.document_management.Controller;
import com.document_management.AWSS3Service;
import com.document_management.DTO.DocumentDetailsDto;
import com.document_management.DTO.DocumentDto;
import com.document_management.Entity.*;
import com.document_management.Repository.*;
import com.document_management.Service.DocumentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.core.io.Resource;

@RestController
@RequestMapping("api/documents")

    public class DocumentController {
    private final DocumentService documentService;
    private UsersRepository usersRepository;
    private PropertyRepository propertyRepository;
    private ModelMapper modelMapper;
    private final DocumentRepository documentRepository;
    private DocTypeRepository docTypeRepository;
    private DocMimeTypeRepository docMimeTypeRepository;
    private final AWSS3Service awsS3Service;

    @Autowired
    public DocumentController(DocumentService documentService, UsersRepository usersRepository, ModelMapper modelMapper,
                              DocumentRepository documentRepository, PropertyRepository propertyRepository,
                              DocMimeTypeRepository docMimeTypeRepository, DocTypeRepository docTypeRepository,
                              AWSS3Service awsS3Service) {
        this.documentService = documentService;
        this.usersRepository = usersRepository;
        this.modelMapper = modelMapper;
        this.documentRepository = documentRepository;
        this.propertyRepository = propertyRepository;
        this.docTypeRepository = docTypeRepository;
        this.docMimeTypeRepository = docMimeTypeRepository;
        this.awsS3Service = awsS3Service;
    }

    @GetMapping("/count")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Integer> countDocument() {
        int count = documentService.getAllDocuments().size();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/{documentId}/download")
    @CrossOrigin(origins = "http://localhost:4200")

    public ResponseEntity<Resource> downloadDocument(@PathVariable Integer documentId) throws IOException {
        return documentService.downloadDocument(documentId);
    }


    @GetMapping("/documentsdetails")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<DocumentDetailsDto> getAllDocumentsWithDetails() {
        List<Document> documents = documentRepository.findAll();
        List<DocumentDetailsDto> documentDetails = new ArrayList<>();
        for (Document document : documents) {
            DocumentDetailsDto details = new DocumentDetailsDto();
            details.setDocumentId(document.getDocumentId());
            details.setDocumentName(document.getDocumentName());
            Users user = document.getUser();
            if (user != null) {
                details.setUserName(user.getUsername());
            }
            Property property = document.getProperty();
            if (property != null) {
                details.setPropertyName(property.getPropertyName());
            }
            DocType docType = document.getDocType();
            if (docType != null) {
                details.setDocTypeName(docType.getDocTypeName());
            }
            DocMimeType docMimeType = document.getDocMimeType();
            if (docMimeType != null) {
                details.setDocMimeTypeName(docMimeType.getDocMimeTypeName());
            }
                documentDetails.add(details);
        }
        return documentDetails;
    }

//    @GetMapping("/documents/propertyname")
//    @CrossOrigin(origins = "http://localhost:4200")
//    public List<Document> searchDocumentsByPropertyName(@RequestParam("propertyName") String propertyName) {
//        return documentRepository.findByPropertyPropertyName(propertyName);
//    }
@GetMapping("/documents/propertyname")
@CrossOrigin(origins = "http://localhost:4200")
public List<DocumentDetailsDto> searchDocumentsByPropertyName(@RequestParam("propertyName") String propertyName) {
    List<Document> documents = documentRepository.findByPropertyPropertyName(propertyName);
    return convertToDocumentDetailsDto(documents);
}

    private List<DocumentDetailsDto> convertToDocumentDetailsDto(List<Document> documents) {
        List<DocumentDetailsDto> dtos = new ArrayList<>();
        for (Document document : documents) {
            DocumentDetailsDto dto = new DocumentDetailsDto();
            dto.setDocumentId(document.getDocumentId());
            dto.setDocumentName(document.getDocumentName());
            dto.setUserName(document.getUser().getUsername());
            dto.setPropertyName(document.getProperty().getPropertyName());
            dto.setDocTypeName(document.getDocType().getDocTypeName());
            dto.setDocMimeTypeName(document.getDocMimeType().getDocMimeTypeName());
            dtos.add(dto);
        }
        return dtos;
    }



    @GetMapping("/documents/username")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Document> searchDocumentsByUsername(@RequestParam("username") String username) {
        return documentService.searchDocumentsByUsername(username);
    }

    @PostMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<String> addDocument(
            @RequestParam("file") MultipartFile file,
            @RequestParam("documentName") String documentName,
            @RequestParam("username") String username,
            @RequestParam("propertyName") String propertyName,
            @RequestParam("docTypeName") String docTypeName,
            @RequestParam("docMimeTypeName") String docMimeTypeName
    ) {
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

        String publicURL = awsS3Service.uploadFile(file);
        document.setFilePath(publicURL);

        documentService.addDocument(document);

        return ResponseEntity.ok("Document added successfully.");
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Void> deleteDocument(@PathVariable Integer documentId) {
        documentService.deleteDocument(documentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/get")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<DocumentDto>> getAllDocuments() {
        List<Document> documents = documentService.getAllDocuments();
        List<DocumentDto> documentDtos = documents.stream()
                .map(document -> modelMapper.map(document, DocumentDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(documentDtos);
    }

    @GetMapping("/{documentId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Document> getDocumentById(@PathVariable Integer documentId) {
        Document document = documentService.getDocumentById(documentId);
        return ResponseEntity.ok(document);
    }

    @PutMapping("/{documentId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<String> updateDocument(
            @PathVariable Integer documentId,
            @RequestParam("file") MultipartFile file,
            @RequestParam("documentName") String documentName,
            @RequestParam("username") String username,
            @RequestParam("propertyName") String propertyName,
            @RequestParam("docTypeName") String docTypeName,
            @RequestParam("docMimeTypeName") String docMimeTypeName
    ) {
        Document document = documentService.getDocumentById(documentId);

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

        documentService.addDocument(document);

        return ResponseEntity.ok("Document updated successfully.");
    }
}
