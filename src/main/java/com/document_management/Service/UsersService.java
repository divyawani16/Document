package com.document_management.Service;
import com.document_management.DTO.UserDto;
import com.document_management.Entity.Users;
import com.document_management.Repository.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersService {
    private final UsersRepository usersRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UsersService(UsersRepository usersRepository, ModelMapper modelMapper) {
        this.usersRepository = usersRepository;
        this.modelMapper = modelMapper;
    }


    public List<UserDto> getAllUsers() {
        List<Users> users = usersRepository.findAll();
        return users.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    public UserDto getUserById(Integer userId) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return modelMapper.map(user, UserDto.class);
    }

    public UserDto createUser(UserDto userDto) {
        Users user = modelMapper.map(userDto, Users.class);
        Users createdUser = usersRepository.save(user);
        return modelMapper.map(createdUser, UserDto.class);
    }

    public UserDto updateUser(Integer userId, UserDto userDto) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        modelMapper.map(userDto, user);
        Users updatedUser = usersRepository.save(user);
        return modelMapper.map(updatedUser, UserDto.class);
    }
    public Optional<Users> findByUsername(String username) {
        return usersRepository. findByUsername(username);
    }

    public void deleteUser(Integer userId) {
        usersRepository.deleteById(userId);
    }
}

//    private final UsersRepository usersRepository;
//
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
//        return usersRepository.save(existingUser);
//    }

//    public void deleteUser(Long userId) {
//
//        usersRepository.deleteById(userId);
//    }


