package com.document_management.Service;

import com.document_management.DTO.DocumentVersionDto;
import com.document_management.Entity.DocumentVersion;
import com.document_management.Repository.DocumentVersionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@Service
public class DocumentVersionService {

    private final DocumentVersionRepository documentVersionRepository;
    private final ModelMapper modelMapper;

    public DocumentVersionService(DocumentVersionRepository documentVersionRepository, ModelMapper modelMapper) {
        this.documentVersionRepository = documentVersionRepository;
        this.modelMapper = modelMapper;
    }

    public DocumentVersionDto getDocumentVersionById(int docVersionId) {
        DocumentVersion documentVersion = documentVersionRepository.findById(docVersionId)
                .orElseThrow(() ->  new EntityNotFoundException("Document version not found"));
        return modelMapper.map(documentVersion, DocumentVersionDto.class);
    }

    public DocumentVersionDto createDocumentVersion(DocumentVersionDto documentVersionDto) {
        DocumentVersion documentVersion = modelMapper.map(documentVersionDto, DocumentVersion.class);
        documentVersion.setCreatedDate(LocalDateTime.now());
        DocumentVersion savedDocumentVersion = documentVersionRepository.save(documentVersion);
        return modelMapper.map(savedDocumentVersion, DocumentVersionDto.class);
    }
}
