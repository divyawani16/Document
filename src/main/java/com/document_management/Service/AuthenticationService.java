//package com.document_management.Service;
//
//import com.document_management.Entity.JwtRequest;
//import com.document_management.Entity.JwtResponse;
//import com.document_management.Entity.Users;
//import com.document_management.Repository.UsersRepository;
//import com.document_management.util.JwtUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.DisabledException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Qualifier("authenticationService")
//@Service
//public class AuthenticationService {
//    @Autowired
//    private JwtUtil jwtUtil;
//    @Autowired
//    JwtService jwtService;
//    @Autowired
//    private UsersRepository usersRepository;
//
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
//        System.out.println("Method call");
//        String username = jwtRequest.getUserName();
//        String password = jwtRequest.getUserPassword();
//        System.out.println(username + "  " + password);
//        authenticate(username, password);
//
//        UserDetails userDetails = jwtService.loadUserByUsername(username);
//        String newGeneratedToken = jwtUtil.generateToken(userDetails);
//
//
//        Optional<Users> user = usersRepository.findByUsername(username);
//        System.out.println("user detail \n" + user);
//        return new JwtResponse(user, newGeneratedToken);
//    }
//
//    private void authenticate(String username, String password) throws Exception {
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//        } catch (DisabledException e) {
//            throw new Exception("User is disabled", e);
//        } catch (BadCredentialsException e) {
//            throw new Exception("Bad credentials from user",e);
//        }
//    }
//}
