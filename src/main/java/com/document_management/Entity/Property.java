package com.document_management.Entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "Property")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="propertyId")
    private Integer propertyId;

    @Column(name="propertyName")
    private String propertyName;

    @Column(name="address")
    private String address;

    @Column(name="city")
    private String city;

    @Column(name="pincode")
    private String pincode;

    @Column(name="building")
    private String building;

    @Column(name="floorNumber")
    private Integer floorNumber;

    @Column(name="flatNumber")
    private Integer flatNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserId")
    private Users user;


}
