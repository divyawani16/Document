package com.document_management.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
@Entity
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class DocType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DocTypeId")
    private Integer docTypeId;

    @Column(name = "DocTypeName")
    private String docTypeName;
    }


