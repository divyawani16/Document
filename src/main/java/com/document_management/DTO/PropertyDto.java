package com.document_management.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class PropertyDto {
        private Integer propertyId;
        
        private String propertyName;

        private String address;

        private String city;

        private String pincode;

        private String building;

        private int floorNumber;

        private int flatNumber;

}
