package com.document_management.Service;
import com.document_management.DTO.DocumentVersionDto;
import com.document_management.Entity.DocumentVersion;
import com.document_management.Repository.DocumentVersionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentVersionService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DocumentVersionRepository documentVersionRepository;

    public DocumentVersion addDocumentVersion(DocumentVersion documentVersion) {
        return documentVersionRepository.save(documentVersion);
    }
    public List<DocumentVersionDto> getAllDocumentVersion() {
        List<DocumentVersion> documentVersions = documentVersionRepository.findAll();
        return documentVersions.stream()
                .map(documentVersion -> modelMapper.map(documentVersion, DocumentVersionDto.class))
                .collect(Collectors.toList());
    }
    public DocumentVersion getDocumentVersionById(Integer id) {
        return documentVersionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("DocumentVersion not found with id: " + id));
    }

    public void deleteDocumentVersion(Integer id) {
        documentVersionRepository.deleteById(id);
    }

    public DocumentVersion updateDocumentVersion(DocumentVersion documentVersion) {
        return documentVersionRepository.save(documentVersion);
    }



}
