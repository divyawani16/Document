package com.document_management.Entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Property")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PropertyId")
    private Integer propertyId;

    @Column(name = "PropertyName")
    private String propertyName;

    @Column(name = "Address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "pincode")
    private String pincode;

    @Column(name = "Building")
    private String building;

    @Column(name = "FloorNumber")
    private int floorNumber;

    @Column(name = "Flatnumber")
    private int flatNumber;
}
