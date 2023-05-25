package com.document_management.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentMetadata {
    private String documentType;
    private String documentMIME;

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentMIME() {
        return documentMIME;
    }

    public void setDocumentMIME(String documentMIME) {
        this.documentMIME = documentMIME;
    }
}
