//package com.hello.hello.Controller;
//        import java.util.List;
//
//        import com.hello.hello.Entity.Property;
//        import com.hello.hello.Service.PropertyService;
//        import org.springframework.beans.factory.annotation.Autowired;
//        import org.springframework.http.HttpStatus;
//        import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/properties")
//public class PropertyController {
//    @Autowired
//    private PropertyService propertyService;
//
//    @GetMapping("/get")
//    public List<Property> getAllProperties() {
//        return propertyService.getAllProperties();
//    }
//
//    @GetMapping("/{id}")
//    public Property getPropertyById(@PathVariable int id) {
//        return propertyService.getPropertyById(id);
//    }
//
//    @PostMapping("/add")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Property createProperty(@RequestBody Property property) {
//        return propertyService.createProperty(property);
//    }
//
//    @PutMapping("/{id}")
//    public Property updateProperty(@PathVariable int id, @RequestBody Property propertyDetails) {
//        return propertyService.updateProperty(id, propertyDetails);
//    }
//
//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteProperty(@PathVariable int id) {
//        propertyService.deleteProperty(id);
//    }
//}
