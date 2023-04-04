package com.document_management.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name="Documents")
@Data

public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="documentId")
    private Long id;

    @Column(name="name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "UserId",referencedColumnName = "UserId")
    private Users users;

    @OneToOne
    @JoinColumn(name = "DocTypeId", referencedColumnName = "docTypeId")
    private DocType docType;


    @OneToOne
    @JoinColumn(name = "docMimeTypeId", referencedColumnName = "docMimeTypeId")
    private DocMimeType docMimeType;

    @ManyToOne
    @JoinColumn(name = "propertyId",referencedColumnName="propertyId" )
    private Property property;


}
