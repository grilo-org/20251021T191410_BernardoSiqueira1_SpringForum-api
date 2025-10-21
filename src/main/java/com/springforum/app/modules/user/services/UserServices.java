package com.springforum.app.modules.user.services;

import com.springforum.app.modules.user.adapters.UserAdapters;
import com.springforum.app.modules.user.dtos.EditUserCredentialsDTO;
import com.springforum.app.modules.user.dtos.NewUserDTO;
import com.springforum.app.modules.user.dtos.UserProfileDetailsDTO;
import com.springforum.app.modules.user.enums.UserType;
import com.springforum.app.modules.user.model.User;
import com.springforum.app.modules.user.repository.UserRepository;
import com.springforum.app.Shared.Exceptions.UserNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Transactional
    public void createNewUser(NewUserDTO newUserDTO){
        User newForumUser = UserAdapters.toUserEntity(newUserDTO, UserType.USER);
        newForumUser.setUserPassword(passwordEncoder.encode(newForumUser.getUserPassword()));

        userRepository.save(newForumUser);
    }

    @Transactional
    public void createAdminUser(NewUserDTO newUserDTO){
        User newForumUser = UserAdapters.toUserEntity(newUserDTO, UserType.ADMIN);
        newForumUser.setUserPassword(passwordEncoder.encode(newForumUser.getUserPassword()));

        userRepository.save(newForumUser);
    }

    public UserProfileDetailsDTO getUserDetailsById(long userId){
        User userQuery = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        return UserAdapters.userToUserProfileDTO(userQuery);
    }

    @Transactional
    public void editUserCredentials(long userId, EditUserCredentialsDTO editCredentialsDTO){
        User userQuery = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        userQuery.setUserEmail(editCredentialsDTO.newUserEmail());
        userQuery.setUserPassword(passwordEncoder.encode(editCredentialsDTO.newUserPassword()));
    }

    @Transactional
    public void deleteUserById(long userId){
        userRepository.deleteById(userId);
    }

}
