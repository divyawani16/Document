package com.document_management.Controller;
import com.document_management.DTO.PropertyDto;
import com.document_management.Entity.Property;
import com.document_management.Repository.PropertyRepository;
import com.document_management.Service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/properties/")
@CrossOrigin(origins = "http://localhost:4200")
public class PropertyController {
    private PropertyService propertyService;
    private final PropertyRepository propertyRepository;

    @Autowired
    public PropertyController(PropertyService propertyService,
                              PropertyRepository propertyRepository) {
        this.propertyService = propertyService;
        this.propertyRepository = propertyRepository;
    }
    @GetMapping("/count")
    public ResponseEntity<Integer> countProperties() {
        int count = propertyService.getAllProperties().size();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyDto> getPropertyById(@PathVariable Integer id) {
        PropertyDto propertyDto = propertyService.getPropertyById(id);
        return ResponseEntity.ok(propertyDto);
    }

    @PostMapping
    public ResponseEntity<PropertyDto> createProperty(@RequestBody @Valid PropertyDto propertyDto) {
        PropertyDto createdPropertyDto = propertyService.createProperty(propertyDto);
        return new ResponseEntity<>(createdPropertyDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PropertyDto> updateProperty(@PathVariable Integer id, @RequestBody @Valid PropertyDto propertyDto) {
        PropertyDto updatedPropertyDto = propertyService.updateProperty(id, propertyDto);
        return ResponseEntity.ok(updatedPropertyDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Integer id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/get")
    public ResponseEntity<List<PropertyDto>> getAllProperties() {
        List<PropertyDto> propertyDtos = propertyService.getAllProperties();
        return ResponseEntity.ok(propertyDtos);
    }
//    @GetMapping("/propertyname")
//    public ResponseEntity<Optional<Property>> findByPropertyName(@RequestParam("name") String propertyName) {
//        Optional<Property> properties = propertyService.findByPropertyName(propertyName);
//        return ResponseEntity.ok(properties);
//    }


@GetMapping("/propertyname")
public ResponseEntity<Optional<Property>> findByPropertyName() {
    String propertyName = "Smartworks";
    Optional<Property> properties = propertyService.findByPropertyName(propertyName);
    return ResponseEntity.ok(properties);
}

}
