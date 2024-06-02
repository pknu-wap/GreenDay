package com.example.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfoResponse {
    private String email;
    private String name;
    private String accessToken;

    public UserInfoResponse(String email, String name) {
        this.email = email;
        this.name = name;
        this.accessToken = accessToken;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
