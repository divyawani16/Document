package com.document_management.Service;
import com.document_management.Entity.DocumentVersion;
import com.document_management.Repository.DocumentVersionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Service
public class DocumentVersionService {
    private final DocumentVersionRepository repository;

    public DocumentVersionService(DocumentVersionRepository repository) {
        this.repository = repository;
    }

    public DocumentVersion save(DocumentVersion documentVersion) {
        return repository.save(documentVersion);
    }

    public List<DocumentVersion> getAll() {
        return repository.findAll();
    }

    public DocumentVersion getById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Document Version not found"));
    }

//    public void delete(int id) {
//        repository.deleteById(id);
//    }
}
