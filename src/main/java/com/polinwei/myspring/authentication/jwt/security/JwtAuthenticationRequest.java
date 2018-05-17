package com.polinwei.myspring.authentication.jwt.security;

import lombok.Data;

import java.io.Serializable;

@Data
public class JwtAuthenticationRequest implements Serializable {

    private String username;
    private String password;

    public JwtAuthenticationRequest() {
        super();
    }

    public JwtAuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
