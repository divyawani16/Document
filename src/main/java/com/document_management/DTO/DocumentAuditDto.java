package com.document_management.DTO;

import com.document_management.Entity.Stage;
import lombok.Data;

import java.util.Date;

@Data
public class DocumentAuditDto {
    private Long documentAuditId;
    private Stage stage;
    private String finishedBy;
    private Date finishedOn;
}
