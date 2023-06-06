package com.document_management.Repository;

import com.document_management.Entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {
   // List<Document> findByPropertyPropertyName(String propertyName);
    List<Document> findByUserUsername(String username);

    List<Document> findByPropertyPropertyName(@Param("propertyName") String propertyName);

    @Query("SELECT d FROM Document d WHERE d.property.propertyName = 'Smartworks'")
    List<Document> findSmartworksDocuments();

}