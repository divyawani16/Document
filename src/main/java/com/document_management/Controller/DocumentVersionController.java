package com.document_management.Controller;
import com.document_management.DTO.DocumentVersionDto;
import com.document_management.Entity.DocumentVersion;
import com.document_management.Service.DocumentVersionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
    @GetMapping("")
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
}
