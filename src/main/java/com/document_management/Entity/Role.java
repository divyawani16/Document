package com.document_management.Entity;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")

public class Role {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleId")
    private Integer roleId;

    @Column(name = "name")
    private String name;

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public void setName(String name) {
        this.name = name;
    }
}
