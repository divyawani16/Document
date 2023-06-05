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

    private boolean approved;

    public DocumentDto(Integer documentId, String documentName, boolean approved) {
        this.documentId = documentId;
        this.documentName = documentName;
        this.approved = approved;
    }
}

