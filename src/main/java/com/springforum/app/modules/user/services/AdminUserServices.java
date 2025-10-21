package com.springforum.app.modules.user.services;

import com.springforum.app.modules.user.enums.UserType;
import com.springforum.app.modules.user.model.User;
import com.springforum.app.modules.user.repository.UserRepository;
import com.springforum.app.Shared.Exceptions.UserNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminUserServices {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void promoteUserToAdmin(long userId){
        User userQuery = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        userQuery.setUserType(UserType.ADMIN);

        userRepository.save(userQuery);
    }

    @Transactional
    public void suspendUser(long userId){
        User userQuery = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        userQuery.setUserEnabled(false);

        userRepository.save(userQuery);
    }


}
