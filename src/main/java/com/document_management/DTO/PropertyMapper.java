package com.document_management.DTO;

import com.document_management.Entity.Property;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;



@Component
public class PropertyMapper {
    private ModelMapper modelMapper = new ModelMapper();

    public PropertyDto toPropertyDto(Property property) {
        return modelMapper.map(property, PropertyDto.class);
    }

    public Property toProperty(PropertyDto propertyDto) {
        return modelMapper.map(propertyDto, Property.class);
    }


}

