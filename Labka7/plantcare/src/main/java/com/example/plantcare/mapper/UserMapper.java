package com.example.plantcare.mapper;

import com.example.plantcare.dto.UserDto;
import com.example.plantcare.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserDto userDto);
}