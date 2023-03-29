package com.document_management.Service;
import com.document_management.Entity.Document;
import com.document_management.Repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;

    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    public Document getDocumentById(Long id) {
        return documentRepository.findById(id).orElse(null);
    }



    public Document createDocument(Document document) {
        return documentRepository.save(document);
    }

    public Document updateDocument(Long id, Document documentDetails) {
        Document document = getDocumentById(id);

        document.setName(documentDetails.getName());
//        document.setUserId(documentDetails.getUserId());
        document.setDocType(documentDetails.getDocType());
        document.setDocMimeType(documentDetails.getDocMimeType());
     //   document.setDocumentVersion(documentDetails.getDocumentVersion());

        return documentRepository.save(document);
    }

    public void deleteDocument(Long id) {
        Document document = getDocumentById(id);
        documentRepository.delete(document);
    }
}
