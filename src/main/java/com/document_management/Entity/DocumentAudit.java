package com.document_management.Entity;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "DocumentAudit")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DocumentAuditId")
    private Integer documentAuditId;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "StageId")
    private Stage stage;

    @Column(name = "FinishedBy")
    private String finishedBy;

    @Column(name = "FinishedOn")
    private LocalDateTime finishedOn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DocumentVersionId")
    private DocumentVersion documentVersion;

    public String getStageName() {
        if (stage != null) {
            return stage.getStageName();
        }
        return null;
    }
}



