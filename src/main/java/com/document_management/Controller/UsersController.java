package com.document_management.Controller;

import com.document_management.DTO.UserDto;
import com.document_management.Entity.Users;
import com.document_management.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UsersController {

        private UsersService usersService;

        @Autowired
        public UsersController(UsersService usersService) {
                this.usersService = usersService;
        }


        @GetMapping("/")
        public ResponseEntity<List<UserDto>> getAllUsers() {
                List<UserDto> userDtos = usersService.getAllUsers();
                return ResponseEntity.ok(userDtos);
        }
//        @GetMapping("/{userId}")
//        public ResponseEntity<UserDto> getUser(@PathVariable Integer userId) {
//                try {
//                        UserDto userDto = usersService.getUserById(userId);
//                        return ResponseEntity.ok(userDto);
//                } catch (EntityNotFoundException e) {
//                        return ResponseEntity.notFound().build();
//                }
//        }

        @PostMapping("/createUser")
        public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto userDto) {
                UserDto createdUserDto = usersService.createUser(userDto);
                return ResponseEntity.status(HttpStatus.CREATED).body(createdUserDto);
        }

        @PutMapping("/{userId}")
        public ResponseEntity<UserDto> updateUser(@PathVariable Integer userId, @RequestBody @Valid UserDto userDto) {
                try {
                        UserDto updatedUserDto = usersService.updateUser(userId, userDto);
                        return ResponseEntity.ok(updatedUserDto);
                } catch (EntityNotFoundException e) {
                        return ResponseEntity.notFound().build();
                }
        }

        @DeleteMapping("/{userId}")
        public ResponseEntity<Void> deleteUser(@PathVariable Integer userId) {
                try {
                        usersService.deleteUser(userId);
                        return ResponseEntity.noContent().build();
                } catch (EntityNotFoundException e) {
                        return ResponseEntity.notFound().build();
                }
        }
        @GetMapping({"/forAdmin"})
        public String forAdmin() {
                return  "This url is only accessible to admin";
        }

        @GetMapping("/{username}")
        public Optional<Users> findByUsername(@PathVariable String username)
        {
                return this.usersService.findByUsername(username);
        }
}



