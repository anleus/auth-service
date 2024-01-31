package com.anleus.authservice.services;

import com.anleus.authservice.config.PasswordEncoding;
import com.anleus.authservice.dto.CredentialsDto;
import com.anleus.authservice.dto.UserDto;
import com.anleus.authservice.entities.User;
import com.anleus.authservice.mappers.UserMapper;
import com.anleus.authservice.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;

@Service
public class UserService {

    @Autowired
    private UserRespository userRespository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;

    public UserDto loginUser(CredentialsDto credentialsDto) {
        User user = userRespository.findByUsername(credentialsDto.username())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()),
                user.getPassword())) {
            return userMapper.toUserDto(user);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }
}
