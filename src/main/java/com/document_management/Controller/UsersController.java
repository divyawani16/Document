package com.document_management.Controller;
import com.document_management.DTO.UserDto;
import com.document_management.Entity.Users;
import com.document_management.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "https://d2sn5cwr5purir.cloudfront.net")
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
        @PreAuthorize("hasRole('Admin')")
        public String forAdmin() {
                try {
                        return  "This url is only accessible to admin";


                }
                catch (Exception e){
                        throw new RuntimeException("Invalid Token");
                }
        }
        @GetMapping({"/forPropertyOwner"})
        @PreAuthorize("hasRole('owner')")
        public String forPropertyOwner()
        {
                try {
                        return  "This url is only accessible to PropertyOwner";
                }
                catch (Exception ex){
                        throw new RuntimeException("Invalid token");
                }
        }
        @GetMapping({"/forTenant"})
        @PreAuthorize("hasRole('tenant')")
        public String forTenant()
        {
                try {
                        return  "This url is only accessible to Tenant";
                }
                catch (Exception ex){
                        throw new RuntimeException("Invalid token");
                }
        }
        @GetMapping("/{username}")
        public Optional<Users> findByUsername(@PathVariable String username)
        {
                return this.usersService.findByUsername(username);
        }
        @GetMapping("/usernames")
        public ResponseEntity<List<String>> getUsernames() {
                List<String> usernames = usersService.getUsernames();
                return ResponseEntity.ok(usernames);
        }
}




