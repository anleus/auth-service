package com.anleus.authservice.services;

import com.anleus.authservice.models.AppUser;
import com.anleus.authservice.models.Role;
import com.anleus.authservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Using user service");

        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user not valid"));
    }

    public void createNewUser() {
        System.out.println("creating new user");
        AppUser newUser = new AppUser();
        newUser.setUsername("algo");
        newUser.setPassword("algo");
        newUser.setEmail("mail@mamamama.com");

        userRepository.save(newUser);
    }
}
