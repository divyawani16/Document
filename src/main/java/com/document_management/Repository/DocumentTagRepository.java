package com.document_management.Repository;

import com.document_management.Entity.DocumentTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentTagRepository extends JpaRepository<DocumentTag, Long> {
}
