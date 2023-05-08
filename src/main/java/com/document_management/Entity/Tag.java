package com.document_management.Entity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Tag")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="tagId")
    private Long tagId;

    @Column(name="tagName")
    private String tagName;
}
