package com.document_management.Repository;
        import com.document_management.Entity.Tag;
        import org.springframework.data.jpa.repository.JpaRepository;
public interface TagRepository extends JpaRepository<Tag, Long> {
}
