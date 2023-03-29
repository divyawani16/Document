package com.document_management.Repository;




import com.document_management.Entity.DocMimeType;
import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;

@Repository
public interface DocMimeTypeRepository extends JpaRepository<DocMimeType, Long> {
}
