package com.document_management.Entity;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "DocumentAudit")
public class DocumentAudit {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DocumentAuditId")
    private Integer documentAuditId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "StageId")
    private Stage stage;

    @Column(name = "FinishedBy")
    private String finishedBy;

    @Column(name = "FinishedOn")
    private LocalDate finishedOn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DocumentVersionId")
    private DocumentVersion documentVersion;
}
