package com.document_management.Service;

        import com.document_management.Entity.DocumentAudit;
        import com.document_management.Repository.DocumentAuditRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;
        import java.util.List;

@Service
public class DocumentAuditService {

    @Autowired
    private DocumentAuditRepository documentAuditRepository;

    public List<DocumentAudit> getAllDocumentAudits() {
        return documentAuditRepository.findAll();
    }

    public DocumentAudit getDocumentAuditById(Long id) {
        return documentAuditRepository.findById(id).orElse(null);
    }

    public DocumentAudit saveDocumentAudit(DocumentAudit documentAudit) {
        return documentAuditRepository.save(documentAudit);
    }

//    public void deleteDocumentAuditById(Long id)
//    {
//        documentAuditRepository.deleteById(id);
//    }

}
