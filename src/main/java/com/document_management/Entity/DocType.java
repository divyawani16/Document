package com.document_management.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

    @Entity
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class DocType {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="docTypeId")
        private Long id;


        @Column(name="docName")
        private String name;


    }


