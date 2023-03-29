package com.document_management.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "Property")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    @JoinColumn(name = "UserId",referencedColumnName = "UserId")
    private Users users;



}
