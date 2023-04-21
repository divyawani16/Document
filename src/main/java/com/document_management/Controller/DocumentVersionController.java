package com.document_management.Controller;

import com.document_management.DTO.DocumentVersionDto;
import com.document_management.Entity.DocumentVersion;
import com.document_management.Service.DocumentVersionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/documentVersions")
public class DocumentVersionController {

    @Autowired
    private DocumentVersionService documentVersionService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<DocumentVersionDto> addDocumentVersion(@RequestBody DocumentVersionDto documentVersionDto) {
        DocumentVersion documentVersion = modelMapper.map(documentVersionDto, DocumentVersion.class);
        documentVersion = documentVersionService.addDocumentVersion(documentVersion);
        DocumentVersionDto response = modelMapper.map(documentVersion, DocumentVersionDto.class);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
