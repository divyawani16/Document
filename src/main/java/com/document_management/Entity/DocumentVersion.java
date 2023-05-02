package com.document_management.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "DocumentVersion")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class DocumentVersion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DocumentVersionId")
    private Integer documentVersionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DocumentId", referencedColumnName = "DocumentId")
    private Document documentId;

    @Column(name = "VersionNumber")
    private Integer versionNumber;

    @Column(name = "Location")
    private String location;

    @Column(name = "CreatedBy")
    private String createdBy;

    @Column(name = "CreatedDate")
    private LocalDateTime createdDate;

    @Column(name = "UpdatedBy")
    private String updatedBy;

    @Column(name = "UpdatedDate")
    private LocalDateTime updatedDate;


}
