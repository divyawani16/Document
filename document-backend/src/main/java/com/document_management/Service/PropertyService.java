package com.document_management.Service;

import com.document_management.DTO.PropertyDto;
import com.document_management.Entity.Property;
import com.document_management.Repository.PropertyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyService {
    private PropertyRepository propertyRepository;
    private ModelMapper modelMapper;

    @Autowired
    public PropertyService(PropertyRepository propertyRepository, ModelMapper modelMapper) {
        this.propertyRepository = propertyRepository;
        this.modelMapper = modelMapper;
    }

    public PropertyDto getPropertyById(Integer propertyId) {
        Property property = propertyRepository.findById(propertyId).orElseThrow(() -> new EntityNotFoundException("Property not found"));
        return modelMapper.map(property, PropertyDto.class);
    }

    public PropertyDto createProperty(PropertyDto propertyDto) {
        Property property = modelMapper.map(propertyDto, Property.class);
        Property createdProperty = propertyRepository.save(property);
        return modelMapper.map(createdProperty, PropertyDto.class);
    }

    public PropertyDto updateProperty(Integer propertyId, PropertyDto propertyDto) {
        Property property = propertyRepository.findById(propertyId).orElseThrow(() -> new EntityNotFoundException("Property not found"));
        modelMapper.map(propertyDto, property);
        Property updatedProperty = propertyRepository.save(property);
        return modelMapper.map(updatedProperty, PropertyDto.class);
    }

    public void deleteProperty(Integer propertyId) {
        propertyRepository.deleteById(propertyId);
    }

    public List<PropertyDto> getAllProperties() {
        List<Property> properties = propertyRepository.findAll();
        return properties.stream()
                .map(property -> modelMapper.map(property, PropertyDto.class))
                .collect(Collectors.toList());
    }
}
