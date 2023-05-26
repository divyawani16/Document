package com.document_management.Repository;
import com.document_management.Entity.DocMimeType;
import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocMimeTypeRepository extends JpaRepository<DocMimeType, Integer> {


 //   Optional<DocMimeType> findByDocMimeTyeName(String docMimeTypeName);

    Optional<DocMimeType> findByDocMimeTypeName(String docMimeTypeName);
}
