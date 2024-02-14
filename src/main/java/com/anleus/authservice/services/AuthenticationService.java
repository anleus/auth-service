package com.anleus.authservice.services;

import com.anleus.authservice.models.AppUser;
import com.anleus.authservice.models.LoginResponseTs;
import com.anleus.authservice.models.Role;
import com.anleus.authservice.repositories.RoleRepository;
import com.anleus.authservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    //TODO change parameters for an AppUserTs object
    public AppUser registerUser(String username, String email, String password) {
        String encodedPassword = passwordEncoder.encode(password);

        Role userRole = roleRepository.findByAuthority("USER").orElse(null);
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);

        return userRepository.save(new AppUser(username, email, encodedPassword, authorities));
    }

    public LoginResponseTs loginUser(String username, String password) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            String token = tokenService.generateJwt(auth);

            AppUser loggedUser = userRepository.findByUsername(username).orElse(null);
            return new LoginResponseTs(loggedUser, token);

        } catch (AuthenticationException e) {
            return new LoginResponseTs(null, "");
        }
    }
}
