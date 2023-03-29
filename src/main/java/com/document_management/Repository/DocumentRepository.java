package com.document_management.Repository;

import com.document_management.Entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

 //   List<Document> findByUserId(Long userId);

}