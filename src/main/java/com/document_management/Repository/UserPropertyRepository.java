package com.document_management.Repository;
import com.document_management.Entity.UserProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface UserPropertyRepository extends JpaRepository<UserProperty, Integer> {



    List<UserProperty> findByPropertyPropertyId(Integer propertyId);

}