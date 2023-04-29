package com.document_management.DTO;
import lombok.Data;
import java.time.LocalDate;
@Data
public class DocumentAuditDto {
    private Integer documentAuditId;
    private Integer stageId;
    private String finishedBy;
    private LocalDate finishedOn;
    private Integer documentVersionId;
}
