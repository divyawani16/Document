//package com.hello.hello.Controller;
//import com.hello.hello.Entity.Users;
//import com.hello.hello.Service.UsersService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/users")
//public class UsersController {
//
//    private final UsersService usersService;
//
//    @Autowired
//    public UsersController(UsersService usersService) {
//        this.usersService = usersService;
//    }
//
//    @GetMapping
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
//@PostMapping("/add")
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
//
//    @DeleteMapping("/{userId}")
//    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
//        usersService.deleteUser(userId);
//        return ResponseEntity.noContent().build();
//    }
//}
//
