 package com.document_management.Service;
import com.document_management.DTO.UserPropertyDto;
import com.document_management.Entity.UserProperty;
import com.document_management.Repository.PropertyRepository;
import com.document_management.Repository.RoleRepository;
import com.document_management.Repository.UserPropertyRepository;
import com.document_management.Repository.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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




    public UserPropertyDto createUserProperty(UserPropertyDto userPropertyDTO) {
        UserProperty userProperty = modelMapper.map(userPropertyDTO, UserProperty.class);
        userProperty.setUser(userRepository.getOne(userPropertyDTO.getUserId()));
        userProperty.setProperty(propertyRepository.getOne(userPropertyDTO.getPropertyId()));
        userProperty.setRole(roleRepository.getOne(userPropertyDTO.getRoleId()));
        userProperty = userPropertyRepository.save(userProperty);
        return modelMapper.map(userProperty, UserPropertyDto.class);
    }
}
