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

    public List<String> getUsernames() {
        List<Users> users = usersRepository.findAll();
        List<String> usernames = users.stream()
                .map(Users::getUsername)
                .collect(Collectors.toList());
        return usernames;
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



//    public Optional<Users> findByusername(String username) {
//        return usersRepository. findByusername(username);
//    }

    public void deleteUser(Integer userId) {
        usersRepository.deleteById(userId);
    }
}
