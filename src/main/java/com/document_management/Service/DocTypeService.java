package com.document_management.Service;

import com.document_management.Entity.DocType;
import com.document_management.Repository.DocTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@ComponentScan
public class DocTypeService {

    @Autowired
    private DocTypeRepository docTypeRepository;

    public List<DocType> getAllDocTypes() {
        return docTypeRepository.findAll();
    }

    public Optional<DocType> getDocTypeById(Long id) {
        return docTypeRepository.findById(id);
    }

    public DocType createDocType(DocType docType) {
        return docTypeRepository.save(docType);
    }

    public DocType updateDocType(Long id, DocType docType) {
        Optional<DocType> optionalDocType = docTypeRepository.findById(id);
        if (optionalDocType.isPresent()) {
            DocType existingDocType = optionalDocType.get();
            existingDocType.setName(docType.getName());
            return docTypeRepository.save(existingDocType);
        } else {
            return null;
        }
    }

//    public void deleteDocType(Long id) {
//        docTypeRepository.deleteById(id);
//    }

}
