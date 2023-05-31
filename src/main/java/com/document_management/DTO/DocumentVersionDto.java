package com.document_management.DTO;
import com.document_management.Entity.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentVersionDto {

    private Integer documentVersionId;
    private Integer documentId;
    private Integer stageId;
    private Integer versionNumber;
    private String location;
    private String createdBy;
    private LocalDateTime createdDate;
    private String updatedBy;
    private LocalDateTime updatedDate;

}
