package com.document_management.Controller;
   //     import com.document_management.DTO.PropertyDTO;
        import com.document_management.DTO.PropertyDto;
        import com.document_management.DTO.PropertyMapper;
        import com.document_management.Entity.Property;
        import com.document_management.Entity.Users;
        import com.document_management.Repository.PropertyRepository;
        import com.document_management.Repository.UsersRepository;
        import com.document_management.Service.PropertyService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;
        import java.util.List;
        import java.util.Map;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {
    @Autowired
    private PropertyService propertyService;
    private PropertyRepository propertyRepository;
private UsersRepository userRepository;


    private final PropertyMapper propertyMapper;

    @Autowired
    public PropertyController(PropertyService propertyService, PropertyMapper propertyMapper) {
        this.propertyService = propertyService;
        this.propertyMapper = propertyMapper;
    }

    @PostMapping("/")
    public PropertyDto createProperty(@RequestBody PropertyDto propertyDto) {
        return propertyMapper.toPropertyDto(propertyService.createProperty(propertyMapper.toProperty(propertyDto)));
    }

    @GetMapping("/{id}")
    public PropertyDto getPropertyById(@PathVariable Integer id) {
        return propertyMapper.toPropertyDto(propertyService.getPropertyById(id));
    }
}
















//    @GetMapping("/")
//    public List<Property> getAllProperties() {
//        return propertyService.getAllProperties();
//    }
//
//    @GetMapping("/{id}")
//    public Property getPropertyById(@PathVariable int id) {
//        return propertyService.getPropertyById(id);
//    }
//
//    @PostMapping("/")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Property createProperty(@RequestBody Property property) {
//        return propertyService.createProperty(property);
//    }
////    @PostMapping("/create")
////    public ResponseEntity<Property> createProperty(@RequestBody Property property) {
////        Property savedProperty = propertyService.createProperty(property);
////        return new ResponseEntity<>(savedProperty, HttpStatus.CREATED);
////    }
//
//
//    @PutMapping("/{id}")
//    public Property updateProperty(@PathVariable int id, @RequestBody Property propertyDetails) {
//        return propertyService.updateProperty(id, propertyDetails);
//    }

//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteProperty(@PathVariable int id) {
//        propertyService.deleteProperty(id);
//    }

//}
