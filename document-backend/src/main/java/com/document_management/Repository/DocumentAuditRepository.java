package com.document_management.Repository;

        import com.document_management.Entity.DocumentAudit;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;


@Repository
public interface DocumentAuditRepository extends JpaRepository<DocumentAudit, Integer> {

}
