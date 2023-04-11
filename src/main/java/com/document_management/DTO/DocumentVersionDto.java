package com.document_management.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class DocumentVersionDto {

    private int DocumentVersionId;
    private int versionNumber;
    private String location;
    private String createdBy;
    private Date createdDate;
    private String updatedBy;
    private Date updatedDate;
}
