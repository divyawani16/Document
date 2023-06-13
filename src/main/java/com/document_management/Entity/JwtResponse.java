package com.document_management.Entity;


import java.util.List;

public class JwtResponse {
    private Users user;

    private List<String> userRoles;
    private String jwtToken;


    public JwtResponse(Users user, List<String> userRoles, String jwtToken) {
        this.user = user;
        this.userRoles = userRoles;
        this.jwtToken = jwtToken;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<String> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<String> userRoles) {
        this.userRoles = userRoles;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
