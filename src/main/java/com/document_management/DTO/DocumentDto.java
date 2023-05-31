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
}

