package com.document_management.DTO;
import lombok.Data;

@Data
public class DocumentDto {
    private Long id;
    private String name;
    private Long UserId;
    private Long propertyId;
    private Long docTypeId;
    private Long docMimeTypeId;

}
