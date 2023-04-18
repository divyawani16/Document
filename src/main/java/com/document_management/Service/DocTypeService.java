package com.document_management.Service;

import com.document_management.DTO.DocTypeDto;
import com.document_management.Entity.DocType;
import com.document_management.Repository.DocTypeRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DocTypeService {

    private final DocTypeRepository docTypeRepository;
    private final ModelMapper modelMapper;

    public DocTypeDto getDocTypeById(Integer docTypeId) {
        DocType docType = docTypeRepository.findById(docTypeId)
                .orElseThrow(() -> new EntityNotFoundException("DocType not found"));
        return modelMapper.map(docType, DocTypeDto.class);
    }

    public DocTypeDto createDocType(DocTypeDto docTypeDto) {
        DocType docType = modelMapper.map(docTypeDto, DocType.class);
        DocType createdDocType = docTypeRepository.save(docType);
        return modelMapper.map(createdDocType, DocTypeDto.class);
    }

    public DocTypeDto updateDocType(Integer docTypeId, DocTypeDto docTypeDto) {
        DocType docType = docTypeRepository.findById(docTypeId)
                .orElseThrow(() -> new EntityNotFoundException("DocType not found"));
        modelMapper.map(docTypeDto, docType);
        DocType updatedDocType = docTypeRepository.save(docType);
        return modelMapper.map(updatedDocType, DocTypeDto.class);
    }

    public void deleteDocType(Integer docTypeId) {
        docTypeRepository.deleteById(docTypeId);
    }

    public List<DocTypeDto> getAllDocTypes() {
        List<DocType> docTypes = docTypeRepository.findAll();
        return docTypes.stream()
                .map(docType -> modelMapper.map(docType, DocTypeDto.class))
                .collect(Collectors.toList());
    }
}
