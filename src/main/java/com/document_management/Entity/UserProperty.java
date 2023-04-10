package com.document_management.Entity;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "UserProperty")
public class UserProperty {

    @EmbeddedId
    private UserPropertyId userPropertyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "UserId")
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("propertyId")
    @JoinColumn(name = "PropertyId")
    private Property property;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roleId")
    private Role role;

    @EqualsAndHashCode
    @AllArgsConstructor
    @NoArgsConstructor
    @Embeddable
    public static class UserPropertyId implements Serializable {

        private Long userId;
        private Integer propertyId;


    }
}
