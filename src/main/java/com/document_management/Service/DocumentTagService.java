package com.document_management.Service;
        import com.document_management.Entity.DocumentTag;
        import com.document_management.Repository.DocumentTagRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.List;

@Service
public class DocumentTagService {
    @Autowired
    private DocumentTagRepository documentTagRepository;

    public List<DocumentTag> getAllDocumentTags() {
        return documentTagRepository.findAll();
    }

    public DocumentTag getDocumentTagById(Long id) {
        return documentTagRepository.findById(id).orElse(null);
    }

    public DocumentTag createDocumentTag(DocumentTag documentTag) {
        return documentTagRepository.save(documentTag);
    }

    public void deleteDocumentTag(Long id) {
        documentTagRepository.deleteById(id);
    }
}
