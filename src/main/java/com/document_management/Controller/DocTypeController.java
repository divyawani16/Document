package com.document_management.Controller;
import com.document_management.Entity.DocType;
import com.document_management.Service.DocTypeService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/doctypes")
public class DocTypeController {

    @Autowired
    private DocTypeService docTypeService;
    private static final Logger logger = LogManager.getLogger(DocTypeController.class);
    @GetMapping("/")
    public ResponseEntity<List<DocType>> getAllDocTypes() {
        logger.info("Getting all document  types........................................................");
        List<DocType> docTypes = docTypeService.getAllDocTypes();
        return new ResponseEntity<>(docTypes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocType> getDocTypeById(@PathVariable("id") Long id) {
        Optional<DocType> optionalDocType = docTypeService.getDocTypeById(id);
        if (optionalDocType.isPresent()) {
            DocType docType = optionalDocType.get();
            return new ResponseEntity<>(docType, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/")
    public ResponseEntity<DocType> createDocType(@RequestBody DocType docType) {
        DocType createdDocType = docTypeService.createDocType(docType);
        return new ResponseEntity<>(createdDocType, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocType> updateDocType(@PathVariable("id") Long id, @RequestBody DocType docType) {
        DocType updatedDocType = docTypeService.updateDocType(id, docType);
        if (updatedDocType != null) {
            return new ResponseEntity<>(updatedDocType, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
//    @DeleteMapping("/{id}")
//    public ResponseEntity<HttpStatus> deleteDocType(@PathVariable("id") Long id) {
//        docTypeService.deleteDocType(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

}