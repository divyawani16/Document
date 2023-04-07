package com.document_management.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DocumentAudit")
public class DocumentAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long documentAuditId;

    @ManyToOne
    @JoinColumn(name = "StageId", referencedColumnName = "StageId")
    private Stage stage;

    @Column(name="finishedBy")
    private String finishedBy;

    @Column(name="finishedOn")
    private Date finishedOn;

    @ManyToOne
    @JoinColumn(name = "documentVersionId", referencedColumnName = "DocumentVersionId")
    private DocumentVersion documentVersion;
}
