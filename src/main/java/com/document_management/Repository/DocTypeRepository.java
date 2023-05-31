package com.document_management.Repository;


import com.document_management.Entity.DocType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocTypeRepository extends JpaRepository<DocType, Integer> {
    Optional<DocType> findByDocTypeName(String docTypeName);
}