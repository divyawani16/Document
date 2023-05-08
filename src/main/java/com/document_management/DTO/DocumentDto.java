package com.document_management.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDto {
    private Integer documentId;
    private String documentName;
    private Integer userId;
    private Integer propertyId;
    private Integer docTypeId;
    private Integer docMimeTypeId;

    public Integer getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public Integer getDocTypeId() {
        return docTypeId;
    }

    public void setDocTypeId(Integer docTypeId) {
        this.docTypeId = docTypeId;
    }

    public Integer getDocMimeTypeId() {
        return docMimeTypeId;
    }

    public void setDocMimeTypeId(Integer docMimeTypeId) {
        this.docMimeTypeId = docMimeTypeId;
    }
}

//public class DocumentDto {
//    private Integer documentId;
//    private String documentName;
//    private Users user;
//    private Property property;
//    private DocType docType;
//    private DocMimeType docMimeType;
