package com.document_management.Entity;

import java.util.Optional;

public class JwtResponse {
    private Optional<Users> user;
    private String jwtToken;

    public JwtResponse(Optional<Users> user, String jwtToken) {
        this.user = user;
        this.jwtToken = jwtToken;
    }

    public Optional<Users> getUser() {
        return user;
    }

    public void setUser(Optional<Users> user) {
        this.user = user;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
