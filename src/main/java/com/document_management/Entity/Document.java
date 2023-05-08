package com.document_management.Entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "docMimeTypeId", referencedColumnName = "docMimeTypeId")
    private DocMimeType docMimeType;
}
