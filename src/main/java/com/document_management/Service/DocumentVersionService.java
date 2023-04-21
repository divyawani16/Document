package com.document_management.Service;

import com.document_management.Entity.DocumentVersion;
import com.document_management.Repository.DocumentVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentVersionService {

    @Autowired
    private DocumentVersionRepository documentVersionRepository;

    public DocumentVersion addDocumentVersion(DocumentVersion documentVersion) {
        return documentVersionRepository.save(documentVersion);
    }
}
