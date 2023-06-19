package com.document_management.Controller;
import com.document_management.DTO.UserPropertyDto;
import com.document_management.Entity.UserProperty;
import com.document_management.Repository.PropertyRepository;
import com.document_management.Repository.RoleRepository;
import com.document_management.Repository.UserPropertyRepository;
import com.document_management.Service.UserPropertyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/userProperty")
public class UserPropertyController {
    private PropertyRepository propertyRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserPropertyRepository userPropertyRepository;
    @Autowired
    private UserPropertyService userPropertyService;

    @PostMapping("/add")
    public UserProperty addUserProperty(@RequestBody UserProperty userProperty) {
        return userPropertyService.addUserProperty(userProperty);
    }
    @PostMapping("/")
    public ResponseEntity<UserPropertyDto> createUserProperty(@RequestBody UserPropertyDto userPropertyDTO) {
        UserPropertyDto createdUserPropertyDTO = userPropertyService.createUserProperty(userPropertyDTO);
        return new ResponseEntity<>(createdUserPropertyDTO, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public UserProperty getUserProperty(@PathVariable Integer id) {
        return userPropertyService.getUserProperty(id);
    }
    @GetMapping("/")
    public List<UserProperty> getAllUserProperties() {
        return userPropertyService.getAllUserProperties();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserProperty(@PathVariable Integer id) {
        userPropertyService.deleteUserProperty(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/property/{propertyId}")
    public ResponseEntity<List<UserPropertyDto>> getUsersDetailsbyPropertyId(@PathVariable Integer propertyId) {
        List<UserPropertyDto> userPropertyDtos = userPropertyService.getUsersDetailsbyPropertyId(propertyId);
        if (userPropertyDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userPropertyDtos, HttpStatus.OK);
    }

}
