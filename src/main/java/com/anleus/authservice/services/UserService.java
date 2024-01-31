package com.anleus.authservice.services;

import com.anleus.authservice.dto.CredentialsDto;
import com.anleus.authservice.dto.UserDto;
import com.anleus.authservice.entities.User;
import com.anleus.authservice.exceptions.AuthException;
import com.anleus.authservice.mappers.UserMapper;
import com.anleus.authservice.repository.UserRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRespository userRespository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserDto loginUser(CredentialsDto credentialsDto) {
        User user = userRespository.findByUsername(credentialsDto.username())
                .orElseThrow(() -> new AuthException("Unknown user", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()),
                user.getPassword())) {
            return userMapper.toUserDto(user);
        }
        throw new AuthException("Invalid password", HttpStatus.BAD_REQUEST);
    }
}
