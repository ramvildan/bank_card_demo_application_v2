package com.javamaster.bank_card_demo_application_v2.converter;

import com.javamaster.bank_card_demo_application_v2.dto.UserDto;
import com.javamaster.bank_card_demo_application_v2.entity.User;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class UserConverter {

    public User fromUserDtoToUser(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .patronymic(userDto.getPatronymic())
                .phoneNumber(userDto.getPhoneNumber())
                .email(userDto.getEmail())
                .status(userDto.getStatus())
                .build();
    }

    public UserDto fromUserToUserDto(User user){
        if(isNull(user)) {
            return null;
        }
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .patronymic(user.getPatronymic())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .build();
    }
}
