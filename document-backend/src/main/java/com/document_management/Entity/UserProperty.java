package com.document_management.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "UserProperty")
public class UserProperty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserPropertyId")
    private Integer userPropertyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserId",referencedColumnName = "UserId")
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PropertyId",referencedColumnName = "PropertyId")
    private Property property;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roleId",referencedColumnName = "roleId")
    private Role role;

   
}
