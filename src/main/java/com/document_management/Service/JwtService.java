package com.document_management.Service;

import com.document_management.Entity.JwtRequest;
import com.document_management.Entity.JwtResponse;
import com.document_management.Entity.UserProperty;
import com.document_management.Entity.Users;
import com.document_management.Repository.UserPropertyRepository;
import com.document_management.Repository.UsersRepository;
import com.document_management.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JwtService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserPropertyRepository userPropertyRepository;
    @Autowired
    private JwtUtil jwtUtil;

//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
//        String username = jwtRequest.getUserName();
//        String password = jwtRequest.getUserPassword();
//        authenticate(username, password);
//
//        UserDetails userDetails = loadUserByUsername(username);
//        String newGeneratedToken = jwtUtil.generateToken(userDetails);
//
//        Users user = usersRepository.findByUsername(username);
//        return new JwtResponse(user, newGeneratedToken);
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository.findByUsername(username).get();
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    getAuthority(user)

            );
        } else {
            throw new UsernameNotFoundException("Username is not valid");
        }
    }



        private Set getAuthority (Users tblUser){
            Set<SimpleGrantedAuthority> authorities = new HashSet<>();

          List<String> Roles = usersRepository.getRole(tblUser.getUserId());
            for (String Role : Roles) {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + Role));
            };
            return authorities;


        }

//        private void authenticate(String username, String password) throws Exception {
//            try {
//                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//            } catch (DisabledException e) {
//                throw new Exception("User is disabled", e);
//            } catch (BadCredentialsException e) {
//                throw new Exception("Bad credentials from user",e);
//            }
//        }
    }




