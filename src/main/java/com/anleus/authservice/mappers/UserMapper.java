package com.anleus.authservice.mappers;

import com.anleus.authservice.dto.UserDto;
import com.anleus.authservice.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);
}
