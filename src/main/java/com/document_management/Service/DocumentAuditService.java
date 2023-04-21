//package com.document_management.Service;
//
//import com.document_management.DTO.DocumentAuditDto;
//import com.document_management.DTO.DocumentAuditRequestDto;
//import com.document_management.Entity.DocumentAudit;
//import com.document_management.Repository.DocumentAuditRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.persistence.EntityNotFoundException;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class DocumentAuditService {
//
//    @Autowired
//    private DocumentAuditRepository documentAuditRepository;
//
//    public DocumentAuditDto createDocumentAudit(DocumentAuditRequestDto documentAuditRequestDto) {
//        DocumentAudit documentAudit = new DocumentAudit();
//        documentAudit.setStageId(documentAuditRequestDto.getStageId());
//        documentAudit.setFinishedBy(documentAuditRequestDto.getFinishedBy());
//        documentAudit.setFinishedOn(documentAuditRequestDto.getFinishedOn());
//        documentAudit.setDocumentVersionId(documentAuditRequestDto.getDocumentVersionId());
//        DocumentAudit savedDocumentAudit = documentAuditRepository.save(documentAudit);
//        return convertToDto(savedDocumentAudit);
//    }
//
//    public DocumentAuditDto getDocumentAuditById(Integer id) {
//        DocumentAudit documentAudit = documentAuditRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Document audit not found with id " + id));
//        return convertToDto(documentAudit);
//    }
//
//    public List<DocumentAuditDto> getAllDocumentAudits() {
//        List<DocumentAudit> documentAudits = documentAuditRepository.findAll();
//        return documentAudits.stream().map(this::convertToDto).collect(Collectors.toList());
//    }
//
//    public DocumentAuditDto updateDocumentAudit(Integer id, DocumentAuditRequestDto documentAuditRequestDto) {
//        DocumentAudit documentAudit = documentAuditRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Document audit not found with id " + id));
//        documentAudit.setStageId(documentAuditRequestDto.getStageId());
//        documentAudit.setFinishedBy(documentAuditRequestDto.getFinishedBy());
//        documentAudit.setFinishedOn(documentAuditRequestDto.getFinishedOn());
//        documentAudit.setDocumentVersionId(documentAuditRequestDto.getDocumentVersionId());
//        DocumentAudit updatedDocumentAudit = documentAuditRepository.save(documentAudit);
//        return convertToDto(updatedDocumentAudit);
//    }
//
//    public void deleteDocumentAudit(Integer id) {
//        DocumentAudit documentAudit = documentAuditRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Document audit not found with id " + id));
//        documentAuditRepository.delete(documentAudit);
//    }
//
//    private DocumentAuditDto convertToDto(DocumentAudit documentAudit) {
//        DocumentAuditDto documentAuditDto = new DocumentAuditDto();
//        documentAuditDto.setId(documentAudit.getId());
//        documentAuditDto.setStageId(documentAudit.getStageId());
//        documentAuditDto.setFinishedBy(documentAudit.getFinishedBy());
//        documentAuditDto.setFinishedOn(documentAudit.getFinishedOn());
//        documentAuditDto.setDocumentVersionId(documentAudit.getDocumentVersionId());
//        return documentAuditDto;
//    }
//}
