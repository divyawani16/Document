package com.document_management.DTO;
import com.document_management.Entity.DocMimeType;
import com.document_management.Entity.DocType;
import com.document_management.Entity.Property;
import com.document_management.Entity.Users;
import lombok.Data;

@Data
public class DocumentDto {
    private Integer documentId;
    private String documentName;
    private Users user;
    private Property property;
    private DocType docType;
    private DocMimeType docMimeType;



}