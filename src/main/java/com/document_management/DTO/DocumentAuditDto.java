package com.document_management.DTO;

import com.document_management.Entity.Stage;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class DocumentAuditDto {
    private Integer documentAuditId;
    private Integer stageId;
    private String finishedBy;
    private LocalDate finishedOn;
    private Integer documentVersionId;
}
