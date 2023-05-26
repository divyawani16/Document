package com.document_management.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "DocumentFiles")
public class DocumentFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fileId;

    @Column(name = "fileData", length = Integer.MAX_VALUE)
    private byte[] fileData;

    @Column(name = "fileName")
    private String fileName;

    @OneToOne(mappedBy = "documentFile")
    private Document document;


}
