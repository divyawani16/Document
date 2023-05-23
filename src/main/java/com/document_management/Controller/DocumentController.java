package com.document_management.Controller;

//import com.document_management.DTO.DocumentDetailsDto;
import com.document_management.DTO.DocumentDetailsDto;
import com.document_management.DTO.DocumentDto;
import com.document_management.Entity.*;
import com.document_management.Repository.DocumentRepository;
import com.document_management.Repository.UsersRepository;
import com.document_management.Service.DocumentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/documents")
@CrossOrigin(origins = "http://localhost:4200")
public class DocumentController {

    private final DocumentService documentService;
    private UsersRepository usersRepository;
    private ModelMapper modelMapper;
    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentController(DocumentService documentService, UsersRepository usersRepository, ModelMapper modelMapper,
                              DocumentRepository documentRepository) {
        this.documentService = documentService;
        this.usersRepository = usersRepository;
        this.modelMapper = modelMapper;
        this.documentRepository = documentRepository;
    }
    @GetMapping("/count")
    public ResponseEntity<Integer> countDocuments() {
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
                details.setDocMimeTypeName(docMimeType.getName());
            }

            documentDetails.add(details);
        }

        return documentDetails;
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


    @PostMapping
    public ResponseEntity<DocumentDto> createDocument(@RequestBody DocumentDto documentDto) {
        Document document = modelMapper.map(documentDto, Document.class);
        document.setUser(usersRepository.getOne(documentDto.getUserId()));
        document = documentService.addDocument(document);
        DocumentDto createdDocumentDto = modelMapper.map(document, DocumentDto.class);
        return new ResponseEntity<>(createdDocumentDto, HttpStatus.CREATED);
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
