 package com.document_management.Service;
import com.document_management.DTO.PropertyDto;
import com.document_management.DTO.UserPropertyDto;
import com.document_management.Entity.Property;
import com.document_management.Entity.Role;
import com.document_management.Entity.UserProperty;
import com.document_management.Entity.Users;
import com.document_management.Repository.PropertyRepository;
import com.document_management.Repository.RoleRepository;
import com.document_management.Repository.UserPropertyRepository;
import com.document_management.Repository.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

 @Service
public class UserPropertyService {

    @Autowired
    private UserPropertyRepository userPropertyRepository;
    @Autowired
    private UsersRepository userRepository;
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ModelMapper modelMapper;

    public UserProperty addUserProperty(UserProperty userProperty) {
        return userPropertyRepository.save(userProperty);
    }

    public UserProperty getUserProperty(Integer id) {
        return userPropertyRepository.findById(id).orElse(null);
    }

    public List<UserProperty> getAllUserProperties() {
        return userPropertyRepository.findAll();
    }
    public UserPropertyDto createUserProperty(UserPropertyDto userPropertyDTO) {
        UserProperty userProperty = modelMapper.map(userPropertyDTO, UserProperty.class);
        userProperty.setUser(userRepository.getOne(userPropertyDTO.getUserId()));
        userProperty.setProperty(propertyRepository.getOne(userPropertyDTO.getPropertyId()));
        userProperty.setRole(roleRepository.getOne(userPropertyDTO.getRoleId()));
        userProperty = userPropertyRepository.save(userProperty);
        return modelMapper.map(userProperty, UserPropertyDto.class);
    }
     public void deleteUserProperty(Integer id) {
         userPropertyRepository.deleteById(id);
     }

     public List<UserPropertyDto> getUsersDetailsbyPropertyId(Integer propertyId) {
         List<UserProperty> userProperties = userPropertyRepository.findByPropertyPropertyId(propertyId);
         return userProperties.stream()
                 .map(this::convertToDto)
                 .collect(Collectors.toList());
     }

     private UserPropertyDto convertToDto(UserProperty userProperty) {
         return UserPropertyDto.builder()
                 .userPropertyId(userProperty.getUserPropertyId())
                 .userId(userProperty.getUser().getUserId())
                 .propertyId(userProperty.getProperty().getPropertyId())
                 .roleId(userProperty.getRole().getRoleId())
                 .build();
     }


 }

