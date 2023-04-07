package com.document_management.Service;
import com.document_management.DTO.UserDto;
import com.document_management.Entity.Role;
import com.document_management.Entity.Users;
import com.document_management.Repository.RoleRepository;
import com.document_management.Repository.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersService {

    private final UsersRepository usersRepository;
private RoleRepository roleRepository;


    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Users getUserById(Long userId) {
        return usersRepository.findById(userId).orElse(null);
    }

    public Users createUser(Users user) {
        return usersRepository.save(user);
    }














//    @Autowired
//    public UsersService(UsersRepository usersRepository) {
//        this.usersRepository = usersRepository;
//    }
//
//    public List<Users> getUsers() {
//        return usersRepository.findAll();
//    }
//
//
//    public Users getUserById(Long userId) {
//        return usersRepository.findById(userId).orElse(null);
//    }
//
//    public Users addUser(Users user) {
//        return usersRepository.save(user);
//    }
//
//    public Users updateUser(Long userId, Users user) {
//        Users existingUser = getUserById(userId);
//        existingUser.setFirstName(user.getFirstName());
//        existingUser.setLastName(user.getLastName());
//        existingUser.setPhoneNumber(user.getPhoneNumber());
//        existingUser.setEmailId(user.getEmailId());
//        existingUser.setRole(user.getRole());
//        return usersRepository.save(existingUser);
//    }



//    @Override
//    public UserDto updateUser(Long userId, UserDto userDto) {
//        Users user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with id " + userId));
//        user.setFirstName(userDto.getFirstName());
//        user.setLastName(userDto.getLastName());
//        user.setPhoneNumber(userDto.getPhoneNumber());
//        user.setEmailId(userDto.getEmailId());
//        user.setUsername(userDto.getUsername());
//        user.setPassword(userDto.getPassword());
//        Role role = roleRepository.findById(userDto.getRoleId()).orElseThrow(() -> new UserNotFoundException("Role not found with id " + userDto.getRoleId()));
//        user.setRole(role);
//        Users updatedUser = userRepository.save(user);
//        return convertToDto(updatedUser);
//    }
//
//    @Override
//    public void deleteUser(Long userId) {
//        userRepository.deleteById(userId);
//    }


//}
//    public void deleteUser(Long userId) {
//
//        usersRepository.deleteById(userId);
//    }

}
