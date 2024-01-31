package com.anleus.authservice.controllers;

import com.anleus.authservice.dto.CredentialsDto;
import com.anleus.authservice.dto.UserDto;
import com.anleus.authservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserDto> loginUser(@RequestBody CredentialsDto credentialsDto) {
        UserDto user = userService.loginUser(credentialsDto);
        return ResponseEntity.ok(user);
    }
}
