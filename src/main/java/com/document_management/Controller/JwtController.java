package com.document_management.Controller;

import com.document_management.Entity.JwtRequest;
import com.document_management.Entity.JwtResponse;
//import com.document_management.Service.AuthenticationService;
import com.document_management.Service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
@CrossOrigin
public class JwtController {

    @Autowired
    private JwtService jwtService;

//    @Autowired
//    private AuthenticationService authenticationService;
    @PostMapping({"/authenticate"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception{
      return jwtService.createJwtToken(jwtRequest);
    }
}
