package com.document_management.Service;
import com.document_management.DTO.DocMimeTypeDto;
import com.document_management.Entity.DocMimeType;
import com.document_management.Repository.DocMimeTypeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class DocMimeTypeService {
    private DocMimeTypeRepository docMimeTypeRepository;
    private ModelMapper modelMapper;

    @Autowired
    public DocMimeTypeService(DocMimeTypeRepository docMimeTypeRepository, ModelMapper modelMapper) {
        this.docMimeTypeRepository = docMimeTypeRepository;
        this.modelMapper = modelMapper;
    }

    public DocMimeTypeDto getDocMimeTypeById(Integer docMimeTypeId) {
        DocMimeType docMimeType = docMimeTypeRepository.findById(docMimeTypeId).orElseThrow(() -> new EntityNotFoundException("Document mime type not found"));
        return modelMapper.map(docMimeType, DocMimeTypeDto.class);
    }

    public DocMimeTypeDto createDocMimeType(DocMimeTypeDto docMimeTypeDto) {
        DocMimeType docMimeType = modelMapper.map(docMimeTypeDto, DocMimeType.class);
        DocMimeType createdDocMimeType = docMimeTypeRepository.save(docMimeType);
        return modelMapper.map(createdDocMimeType, DocMimeTypeDto.class);
    }

    public DocMimeTypeDto updateDocMimeType(Integer docMimeTypeId, DocMimeTypeDto docMimeTypeDto) {
        DocMimeType docMimeType = docMimeTypeRepository.findById(docMimeTypeId).orElseThrow(() -> new EntityNotFoundException("Document mime type not found"));
        modelMapper.map(docMimeTypeDto, docMimeType);
        DocMimeType updatedDocMimeType = docMimeTypeRepository.save(docMimeType);
        return modelMapper.map(updatedDocMimeType, DocMimeTypeDto.class);
    }

    public void deleteDocMimeType(Integer docMimeTypeId) {
        docMimeTypeRepository.deleteById(docMimeTypeId);
    }

    public List<DocMimeTypeDto> getAllDocMimeTypes() {
        List<DocMimeType> docMimeTypes = docMimeTypeRepository.findAll();
        return docMimeTypes.stream()
                .map(docMimeType -> modelMapper.map(docMimeType, DocMimeTypeDto.class))
                .collect(Collectors.toList());
    }
}
