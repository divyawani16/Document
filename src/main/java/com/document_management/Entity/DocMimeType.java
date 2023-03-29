package com.document_management.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DocMimeType")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocMimeType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="docMimeTypeId")
    private Long id;

    @Column(name = "docMimeName")
    private String name;
}
