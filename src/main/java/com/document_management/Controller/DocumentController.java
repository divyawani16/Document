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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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
    public ResponseEntity<DocumentDto> getDocumentById(@PathVariable Integer documentId) {
        DocumentDto document = documentService.getDocumentById(documentId);
        return ResponseEntity.ok(document);
    }
}
//    @GetMapping("/documents/{documentId}/details")
//    public ResponseEntity<DocumentDetailsDto> getDocumentDetails(@PathVariable int documentId) {
//        DocumentDetailsDto documentDetails = documentService.getDocumentDetails(documentId);
//        if (documentDetails == null) {
//            return ResponseEntity.notFound().build();
//        } else {
//            return ResponseEntity.ok(documentDetails);
//        }
//    }

//    @PostMapping
//    public ResponseEntity<DocumentDto> createDocument(@RequestBody DocumentDto documentDto) {
//        DocumentDto createdDocument = documentService.createDocument(documentDto);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdDocument);
//    }
//@PostMapping("/documents")
//    @PostMapping
//    public Document createDocument(@RequestBody Document document) {
//        DocumentVersion documentVersion = new DocumentVersion();
//        documentVersion.setVersionNumber(1);
//        documentVersion.setLocation("location/of/document/version");
//        documentVersion.setCreatedBy("user");
//
//        // Set the associated stage based on your requirements
//        // documentVersion.setStage(stage);
//
//        document.setDocumentVersion(documentVersion);
//        documentService.createDocument(document);
//        return document;
//    }
//public ResponseEntity<String> addDocument(
//        @RequestParam("file") MultipartFile file,
//        @RequestParam("documentName") String documentName,
//        @RequestParam("userId") Integer userId,
//        @RequestParam("propertyId") Integer propertyId,
//        @RequestParam("docTypeId") Integer docTypeId,
//        @RequestParam("docMimeTypeId") Integer docMimeTypeId
//) {
//    try {
//        Document document = new Document();
//        document.setDocumentName(documentName);
//
//        Users user = usersRepository.getOne(userId);
//        document.setUser(user);
//
//        Property property = documentService.getPropertyById(propertyId);
//        document.setProperty(property);
//
//        DocType docType = documentService.getDocTypeById(docTypeId);
//        document.setDocType(docType);
//
//        DocMimeType docMimeType = documentService.getDocMimeTypeById(docMimeTypeId);
//        document.setDocMimeType(docMimeType);
//
//        String storagePath = documentService.saveFile(file);
//
//        document.setFilePath(storagePath);
//
//        documentService.addDocument(document);
//
//        return ResponseEntity.ok("Document added successfully.");
//    } catch (IOException e) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add document.");
//    }
//}


//    @PostMapping
//    public ResponseEntity<DocumentDto> createDocument(@RequestBody DocumentDto documentDto) {
//        Document document = modelMapper.map(documentDto, Document.class);
//        document.setUser(usersRepository.getOne(documentDto.getUserId()));
//        document = documentService.addDocument(document);
//        DocumentDto createdDocumentDto = modelMapper.map(document, DocumentDto.class);
//        return new ResponseEntity<>(createdDocumentDto, HttpStatus.CREATED);
//    }



//
//@RestController
//@RequestMapping("/api/documents")
//public class DocumentController {
//    private DocumentService documentService;
//
//    @Autowired
//    public DocumentController(DocumentService documentService) {
//        this.documentService = documentService;
//    }
//
//    @GetMapping("/{documentId}")
//    public ResponseEntity<DocumentDto> getDocumentById(@PathVariable Integer documentId) {
//        DocumentDto documentDto = documentService.getDocumentById(documentId);
//        return ResponseEntity.ok(documentDto);
//    }
//
//    @PostMapping("/")
//    public ResponseEntity<DocumentDto> createDocument(@RequestBody DocumentDto documentDto) {
//        DocumentDto createdDocumentDto = documentService.createDocument(documentDto);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdDocumentDto);
//    }
//
//    @PutMapping("/{documentId}")
//    public ResponseEntity<DocumentDto> updateDocument(@PathVariable Integer documentId, @RequestBody DocumentDto documentDto) {
//            DocumentDto updatedDocumentDto = documentService.updateDocument(documentId, documentDto);
//        return ResponseEntity.ok(updatedDocumentDto);
//    }
//
//    @DeleteMapping("/{documentId}")
//    public ResponseEntity<Void> deleteDocument(@PathVariable Integer documentId) {
//        documentService.deleteDocument(documentId);
//        return ResponseEntity.noContent().build();
//    }
//
////    @GetMapping("/user/{userId}")
////    public ResponseEntity<List<DocumentDto>> getAllDocumentsByUserId(@PathVariable Integer userId) {
////        List<DocumentDto> documentDtoList = documentService.getAllDocumentsByUserId(userId);
////        return ResponseEntity.ok(documentDtoList);
////    }
////
////    @GetMapping("/property/{propertyId}")
////    public ResponseEntity<List<DocumentDto>> getAllDocumentsByPropertyId(@PathVariable Integer propertyId) {
////        List<DocumentDto> documentDtoList = documentService.getAllDocumentsByPropertyId(propertyId);
////        return ResponseEntity.ok(documentDtoList);
////    }
//}
