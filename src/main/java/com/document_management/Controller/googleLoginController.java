//package com.document_management.Controller;
//import com.document_management.Service.googleLoginService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RequestMapping("/api")
//@RestController
//@CrossOrigin(origins = "http://localhost:4200/")
//public class googleLoginController {
//        @Autowired
//        private googleLoginService googleloginService;
//        @GetMapping("/role/{emailId}")
//        public ResponseEntity<String> getRole(@PathVariable String emailId) {
//            System.out.println(emailId);
//            return new ResponseEntity<String>(googleloginService.getRole(emailId), HttpStatus.OK);
//        }
//    }
//
