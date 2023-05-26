package com.document_management.Repository;
import com.document_management.Entity.googlelogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GoogleLoginRepository extends JpaRepository<googlelogin, Integer> {

    @Query("SELECT u.Role FROM googlelogin u WHERE u.emailId = :emailId")
    public String getUserRole(@Param("emailId") String emailId);
}
