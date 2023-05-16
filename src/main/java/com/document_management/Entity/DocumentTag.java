package com.document_management.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
@Entity
@Table(name = "DocumentTag")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="documentTagId")
    private Long documentTagId;

    @ManyToOne
    @JoinColumn(name = "DocumentId")
    private Document document;

    @ManyToOne
    @JoinColumn(name = "TagId")
    private Tag tag;
}