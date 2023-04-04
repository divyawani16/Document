package com.document_management.Service;
import com.document_management.Entity.Users;
import com.document_management.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<Users> getUsers() {
        return usersRepository.findAll();
    }


    public Users getUserById(Long userId) {
        return usersRepository.findById(userId).orElse(null);
    }

    public Users addUser(Users user) {
        return usersRepository.save(user);
    }

    public Users updateUser(Long userId, Users user) {
        Users existingUser = getUserById(userId);
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setPhoneNumber(user.getPhoneNumber());
        existingUser.setEmailId(user.getEmailId());
        existingUser.setRole(user.getRole());
        return usersRepository.save(existingUser);
    }

//    public void deleteUser(Long userId) {
//
//        usersRepository.deleteById(userId);
//    }

}
