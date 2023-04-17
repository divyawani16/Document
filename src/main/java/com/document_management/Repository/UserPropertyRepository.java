package com.document_management.Repository;
import com.document_management.Entity.UserProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPropertyRepository extends JpaRepository<UserProperty, Integer> {



}
