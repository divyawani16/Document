package com.document_management.Service;
        import com.document_management.Entity.DocMimeType;
        import com.document_management.Repository.DocMimeTypeRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.List;
        import java.util.Optional;

@Service
public class DocMimeTypeService {

    @Autowired
    private DocMimeTypeRepository docMimeTypeRepository;

    public List<DocMimeType> getAllDocMimeTypes() {
        return docMimeTypeRepository.findAll();
    }

    public Optional<DocMimeType> getDocMimeTypeById(Long id) {
        return docMimeTypeRepository.findById(id);
    }

    public DocMimeType createDocMimeType(DocMimeType docMimeType) {
        return docMimeTypeRepository.save(docMimeType);
    }

    public DocMimeType updateDocMimeType(Long id, DocMimeType docMimeType) {
        Optional<DocMimeType> optionalDocMimeType = docMimeTypeRepository.findById(id);
        if (optionalDocMimeType.isPresent()) {
            DocMimeType existingDocMimeType = optionalDocMimeType.get();
            existingDocMimeType.setName(docMimeType.getName());
            return docMimeTypeRepository.save(existingDocMimeType);
        } else {
            return null;
        }
    }

    public void deleteDocMimeType(Long id) {
        docMimeTypeRepository.deleteById(id);
    }
}
