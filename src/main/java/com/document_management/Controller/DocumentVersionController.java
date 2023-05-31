package com.document_management.Controller;
import com.document_management.DTO.DocumentDetailsDto;
import com.document_management.DTO.DocumentVersionDto;
import com.document_management.Entity.*;
import com.document_management.Repository.DocumentVersionRepository;
import com.document_management.Repository.StageRepository;
import com.document_management.Service.DocumentVersionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/documentVersions")
@CrossOrigin(origins = "http://localhost:4200")
public class DocumentVersionController {

    @Autowired
    private DocumentVersionService documentVersionService;
    @Autowired
    private StageRepository stageRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private DocumentVersionRepository documentVersionRepository;

    @PostMapping
    public ResponseEntity<DocumentVersionDto> addDocumentVersion(@RequestBody DocumentVersionDto documentVersionDto) {
        DocumentVersion documentVersion = modelMapper.map(documentVersionDto, DocumentVersion.class);
        documentVersion = documentVersionService.addDocumentVersion(documentVersion);
        DocumentVersionDto response = modelMapper.map(documentVersion, DocumentVersionDto.class);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping("/")
    public ResponseEntity<List<DocumentVersionDto>> getAllDocumentVersion() {
        List<DocumentVersionDto> documentVersionDtos = documentVersionService.getAllDocumentVersion();
        return ResponseEntity.ok(documentVersionDtos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DocumentVersionDto> getDocumentVersionById(@PathVariable Integer id) {
        DocumentVersion documentVersion = documentVersionService.getDocumentVersionById(id);
        DocumentVersionDto response = modelMapper.map(documentVersion, DocumentVersionDto.class);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocumentVersion(@PathVariable Integer id) {
        documentVersionService.deleteDocumentVersion(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocumentVersionDto> updateDocumentVersion(@PathVariable Integer id, @RequestBody DocumentVersionDto documentVersionDto) {
        DocumentVersion documentVersion = modelMapper.map(documentVersionDto, DocumentVersion.class);
        documentVersion.setDocumentVersionId(id);
        documentVersion = documentVersionService.updateDocumentVersion(documentVersion);
        DocumentVersionDto response = modelMapper.map(documentVersion, DocumentVersionDto.class);
        return ResponseEntity.ok(response);
    }

//    @GetMapping("/documents")
//    public List<DocumentVersionDto> getAllDocumentsWithDetails() {
//        List<DocumentVersion> documents = documentVersionRepository.findAll();
//        List<DocumentVersionDto> documentDetails = new ArrayList<>();
//
//        for (DocumentVersion document : documents) {
//            DocumentVersionDto details = new DocumentVsDto();
//            details.setDocumentName(document.getDocumentName());
//
////            Users user = document.getUser();
////            if (user != null) {
////                details.setUserName(user.getUsername());
////            }
//
////            Property property = document.getProperty();
////            if (property != null) {
////                details.setPropertyName(property.getPropertyName());
////            }
//
//
//            documentDetails.add(details);
//        }
//
//        return documentDetails;
//    }
}
