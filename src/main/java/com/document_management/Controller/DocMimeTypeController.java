package com.document_management.Controller;
import com.document_management.DTO.DocMimeTypeDto;
import com.document_management.Service.DocMimeTypeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/doc-mime-types")
public class DocMimeTypeController {
    private DocMimeTypeService docMimeTypeService;
    private static final Logger logger = LogManager.getLogger(DocMimeTypeController.class);
    @Autowired
    public DocMimeTypeController(DocMimeTypeService docMimeTypeService) {
        this.docMimeTypeService = docMimeTypeService;
    }
    @GetMapping("/{docMimeTypeId}")
    public ResponseEntity<DocMimeTypeDto> getDocMimeTypeById(@PathVariable Integer docMimeTypeId) {
        logger.info("Getting all document  ........");
        DocMimeTypeDto docMimeTypeDto = docMimeTypeService.getDocMimeTypeById(docMimeTypeId);
        return ResponseEntity.ok(docMimeTypeDto);
    }

    @PostMapping
    public ResponseEntity<DocMimeTypeDto> createDocMimeType(@RequestBody DocMimeTypeDto docMimeTypeDto) {
        DocMimeTypeDto createdDocMimeTypeDto = docMimeTypeService.createDocMimeType(docMimeTypeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDocMimeTypeDto);
    }

    @PutMapping("/{docMimeTypeId}")
    public ResponseEntity<DocMimeTypeDto> updateDocMimeType(@PathVariable Integer docMimeTypeId, @RequestBody DocMimeTypeDto docMimeTypeDto) {
        DocMimeTypeDto updatedDocMimeTypeDto = docMimeTypeService.updateDocMimeType(docMimeTypeId, docMimeTypeDto);
        return ResponseEntity.ok(updatedDocMimeTypeDto);
    }

    @DeleteMapping("/{docMimeTypeId}")
    public ResponseEntity<Void> deleteDocMimeType(@PathVariable Integer docMimeTypeId) {
        docMimeTypeService.deleteDocMimeType(docMimeTypeId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("")
    public ResponseEntity<List<DocMimeTypeDto>> getAllDocMimeTypes() {
        List<DocMimeTypeDto> docMimeTypeDtos = docMimeTypeService.getAllDocMimeTypes();
        return ResponseEntity.ok(docMimeTypeDtos);
    }
}
