package com.document_management.Entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Entity
@Table(name="Documents")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DocumentId")
    private Integer documentId;


    @Column(name = "DocumentName")
    private String documentName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserId",referencedColumnName = "UserId")
    private Users user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PropertyId",referencedColumnName="PropertyId" )
    private Property property;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DocTypeId",referencedColumnName="DocTypeId" )
    private DocType docType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DocMimeTypeId",referencedColumnName="docMimeTypeId" )
    private DocMimeType docMimeType;
}