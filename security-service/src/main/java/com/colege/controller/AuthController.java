package com.colege.controller;

import com.colege.dto.TokenDTO;
import com.colege.dto.UserDTO;
import com.colege.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private UserService service;

    @PostMapping("/register")
    public String addNewUser(@RequestBody UserDTO user) {
        return service.create(user);
    }

    @PostMapping("/token")
    public TokenDTO getToken(@RequestBody UserDTO user) {
        TokenDTO token = service.login(user);
        if (token != null) {
            return token;
        } else {
            throw new RuntimeException("invalid access");
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        service.validate(token);
        return "Token is valid";
    }
}