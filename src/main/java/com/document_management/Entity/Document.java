package com.document_management.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Documents")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer documentId;

    @Column(name = "documentName")
    private String documentName;

    @ManyToOne
    @JoinColumn(name = "UserId", referencedColumnName = "UserId")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "PropertyId", referencedColumnName = "propertyId")
    private Property property;

    @ManyToOne
    @JoinColumn(name = "docTypeId", referencedColumnName = "docTypeId")
    private DocType docType;

    @Column(name = "filePath")
    private String filePath;

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    @OneToOne(cascade = CascadeType.ALL)
    private DocumentFile documentFile;

    @ManyToOne
    @JoinColumn(name = "docMimeTypeId", referencedColumnName = "docMimeTypeId")
    private DocMimeType docMimeType;

    private LocalDateTime dateTime;

   private boolean approved;

    }

