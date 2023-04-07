package com.document_management.Controller;
import com.document_management.DTO.UserDto;
import com.document_management.DTO.UserMapper;
import com.document_management.Entity.Users;
import com.document_management.Service.UsersService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UsersService usersService;
    private final UserMapper userMapper;

    @Autowired
    public UsersController(UsersService userService, UserMapper userMapper) {
        this.usersService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/{userId}")
    public UserDto getUserById(@PathVariable Long userId) {
        Users user = usersService.getUserById(userId);
        return userMapper.toUserDto(user);
    }

    @PostMapping("/")
    public UserDto createUser(@RequestBody UserDto userDto) {
        Users user = userMapper.toUser(userDto);
        Users savedUser = usersService.createUser(user);
        return userMapper.toUserDto(savedUser);
    }
}

//    @Autowired
//    public UsersController(UsersService usersService) {
//        this.usersService = usersService;
//    }
//
//    @GetMapping("/")
//    public List<Users> getUsers() {
//        return usersService.getUsers();
//    }
//
//    @GetMapping("/{userId}")
//    public ResponseEntity<Users> getUserById(@PathVariable Long userId) {
//        Users user = usersService.getUserById(userId);
//        return ResponseEntity.ok(user);
//    }
//
//@PostMapping("/")
//public ResponseEntity<String> addUser(@RequestBody Users user) {
//    try {
//        usersService.addUser(user);
//        return ResponseEntity.ok("User added successfully");
//    } catch (Exception e) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add user");
//    }
//}
//    @PutMapping("/{userId}")
//    public ResponseEntity<Users> updateUser(@PathVariable Long userId, @RequestBody Users user) {
//        Users updatedUser = usersService.updateUser(userId, user);
//        return ResponseEntity.ok(updatedUser);
//    }





//    @DeleteMapping("/{userId}")
//    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
//        usersService.deleteUser(userId);
//        return ResponseEntity.noContent().build();
//    }


