package com.document_management.Controller;
import com.document_management.DTO.DocTypeDto;
import com.document_management.Service.DocTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctypes")
public class DocTypeController {

    private final DocTypeService docTypeService;

    @Autowired
    public DocTypeController(DocTypeService docTypeService) {
        this.docTypeService = docTypeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocTypeDto> getDocTypeById(@PathVariable("id") Integer id) {
        DocTypeDto docType = docTypeService.getDocTypeById(id);
        return new ResponseEntity<>(docType, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DocTypeDto> createDocType(@RequestBody DocTypeDto docTypeDto) {
        DocTypeDto createdDocType = docTypeService.createDocType(docTypeDto);
        return new ResponseEntity<>(createdDocType, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocTypeDto> updateDocType(@PathVariable("id") Integer id, @RequestBody DocTypeDto docTypeDto) {
        DocTypeDto updatedDocType = docTypeService.updateDocType(id, docTypeDto);
        return new ResponseEntity<>(updatedDocType, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocType(@PathVariable("id") Integer id) {
        docTypeService.deleteDocType(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<DocTypeDto>> getAllDocTypes() {
        List<DocTypeDto> docTypes = docTypeService.getAllDocTypes();
        return new ResponseEntity<>(docTypes, HttpStatus.OK);
    }
}
