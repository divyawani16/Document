package com.document_management.Repository;
        import com.document_management.Entity.Users;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;
@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

}
