package com.document_management.Service;
        import com.document_management.Entity.Property;
        import com.document_management.Entity.Users;
        import com.document_management.Repository.PropertyRepository;
        import com.document_management.Repository.UsersRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.stereotype.Service;
        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.ResponseStatus;
        import javax.persistence.criteria.CriteriaBuilder;
        import java.util.List;
        import java.util.Optional;


@Service
public class PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;
    private UsersRepository usersRepository;
    @Autowired
    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public Property createProperty(Property property) {
        return propertyRepository.save(property);
    }

    public Property getPropertyById(Integer id) {
        Optional<Property> property = propertyRepository.findById(id);
        return property.orElse(null);
    }
}













//    public List<Property> getAllProperties() {
//        return propertyRepository.findAll();
//    }
//
//    public Property getPropertyById(int id) {
//        return propertyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Property", "id", id));
//    }
//
//    public Property createProperty(Property property) {
//        return propertyRepository.save(property);
//    }
////public Property createProperty(Property property) {
////    return propertyRepository.save(property);
////}
////    public Property createProperty(Property property) {
////        Users users = property.getUsers();
////        if (users != null) {
////            users = usersRepository.save(users);
////            property.setUsers(users);
////        }
////        return propertyRepository.save(property);
////    }
//
//
//    public Property updateProperty(int id, Property propertyDetails) {
//        Property property = getPropertyById(id);
//
//        property.setPropertyName(propertyDetails.getPropertyName());
//        property.setAddress(propertyDetails.getAddress());
//        property.setCity(propertyDetails.getCity());
//        property.setPincode(propertyDetails.getPincode());
//        property.setBuilding(propertyDetails.getBuilding());
//        property.setFloorNumber(propertyDetails.getFloorNumber());
//        property.setFlatNumber(propertyDetails.getFlatNumber());
//
//        return propertyRepository.save(property);
//    }
//
////    public void deleteProperty(int id) {
////        Property property = getPropertyById(id);
////        propertyRepository.delete(property);
////    }
//    @ResponseStatus(value = HttpStatus.NOT_FOUND)
//    public class ResourceNotFoundException extends RuntimeException {
//        public ResourceNotFoundException(String resourceName, String fieldName, int fieldValue) {
//            super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
//        }
//    }

//}
