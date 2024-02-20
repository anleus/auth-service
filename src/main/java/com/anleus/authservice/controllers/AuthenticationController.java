package com.anleus.authservice.controllers;

import com.anleus.authservice.models.AppUser;
import com.anleus.authservice.models.ts.LoginResponseTs;
import com.anleus.authservice.models.ts.LoginTs;
import com.anleus.authservice.models.ts.RegistrationTs;
import com.anleus.authservice.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AppUser> registerUser(@RequestBody RegistrationTs registrationTs) {
        AppUser registeredUser = authenticationService.registerUser(
                registrationTs.username(), registrationTs.email(), registrationTs.password()
        );
        return ResponseEntity.status(HttpStatus.OK)
                .body(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseTs> loginUser(@RequestBody LoginTs loginTs) {
        LoginResponseTs loggedUser = authenticationService.loginUser(
                loginTs.username(), loginTs.password()
        );
        return ResponseEntity.status(HttpStatus.OK)
                .body(loggedUser);
    }
}
