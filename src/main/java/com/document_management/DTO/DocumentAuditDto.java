package com.document_management.DTO;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentAuditDto {

    private Integer documentAuditId;
    private String stageName;
    private String documentName;

    private String finishedBy;

    private LocalDateTime finishedOn;

    private Integer documentVersionId;

}


