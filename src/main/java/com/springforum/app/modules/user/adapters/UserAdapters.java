package com.springforum.app.modules.user.adapters;


import com.springforum.app.modules.user.dtos.NewUserDTO;
import com.springforum.app.modules.user.dtos.UserProfileDetailsDTO;
import com.springforum.app.modules.user.enums.UserType;
import com.springforum.app.modules.user.model.User;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class UserAdapters {

    public static User toUserEntity(NewUserDTO newUserDTO, UserType userType){
        return new User(newUserDTO.userEmail(),
                newUserDTO.userName(),
                newUserDTO.userPassword(),
                newUserDTO.userImageURL(),
                userType
                );
    }

    public static UserProfileDetailsDTO userToUserProfileDTO(User user){
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return new UserProfileDetailsDTO(user.getUserNickname(),
                user.getUserImageURL(),
                user.getUserId(),
                user.getUserCreationDate().format(dateTimeFormat)
        );
    }

}
