package com.document_management.Repository;
        import com.document_management.Entity.Users;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.stereotype.Repository;

        import java.util.List;
        import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
   @Query(value = "select roles.name from roles,users,user_property where roles.role_id = user_property.role_id and users.user_id = user_property.user_id and users.user_id = ? group by roles.name",nativeQuery = true)
        List<String> getRole(Integer id);
        Optional<Users> findByUsername(String username);
        @Query(value = "select r.name \n" +
                "from doc.users u\n" +
                "inner join doc.user_property up on u.user_id = up.user_id\n" +
                "inner join doc.roles r on up.role_id = r.role_id\n" +
                "where Username = ?", nativeQuery = true)
        List<String> getUsrRoleByUserName(String userName);
}