package com.document_management.Controller;
import com.document_management.Entity.DocMimeType;
        import com.document_management.Service.DocMimeTypeService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;
        import java.util.List;
        import java.util.Optional;

@RestController
@RequestMapping("/doc-mime-types")
public class DocMimeTypeController {

    @Autowired
    private DocMimeTypeService docMimeTypeService;

    @GetMapping("get")
    public ResponseEntity<List<DocMimeType>> getAllDocMimeTypes() {
        List<DocMimeType> docMimeTypes = docMimeTypeService.getAllDocMimeTypes();
        return new ResponseEntity<>(docMimeTypes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocMimeType> getDocMimeTypeById(@PathVariable Long id) {
        Optional<DocMimeType> docMimeType = docMimeTypeService.getDocMimeTypeById(id);
        return docMimeType.map(mimeType -> new ResponseEntity<>(mimeType, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("add")
    public ResponseEntity<DocMimeType> createDocMimeType(@RequestBody DocMimeType docMimeType) {
        DocMimeType createdMimeType = docMimeTypeService.createDocMimeType(docMimeType);
        return new ResponseEntity<>(createdMimeType, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<DocMimeType> updateDocMimeType(@PathVariable("id") Long id, @RequestBody DocMimeType docMimeType) {
        DocMimeType updatedDocMimeType = docMimeTypeService.updateDocMimeType(id, docMimeType);
        if (updatedDocMimeType != null) {
            return new ResponseEntity<>(updatedDocMimeType, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocMimeType(@PathVariable("id") Long id) {
        docMimeTypeService.deleteDocMimeType(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}