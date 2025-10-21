package com.springforum.app.modules.user.controller;

import com.springforum.app.modules.user.dtos.EditUserCredentialsDTO;
import com.springforum.app.modules.user.dtos.NewUserDTO;
import com.springforum.app.modules.user.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServices userServices;

    @PostMapping("/create")
    public ResponseEntity<?> createNewUser(@RequestBody @Valid NewUserDTO newUserDTO){
        userServices.createNewUser(newUserDTO);

        return ResponseEntity.status(200).body("Usuário criado com sucesso.");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable(required = true) long userId){
        var serviceResponse = userServices.getUserDetailsById(userId);

        return ResponseEntity.status(200).body(serviceResponse);
    }

    @PutMapping("/edit/{userId}")
    @PreAuthorize("@authorshipVerification.verifyIsAccountOwner(#userId, authentication.name)")
    public ResponseEntity<?> editUser(@PathVariable(required = true) long userId, @RequestBody @Valid EditUserCredentialsDTO editUserCredentialsDTO){
        userServices.editUserCredentials(userId, editUserCredentialsDTO);

        return ResponseEntity.status(200).body("Credenciais do usuário foram atualizadas.");
    }

    @DeleteMapping("/delete/{userId}")
    @PreAuthorize("@authorshipVerification.verifyIsAccountOwner(#userId, authentication.name)")
    public ResponseEntity<?> deleteUser(@PathVariable(required = true) long userId){
        userServices.deleteUserById(userId);

        return ResponseEntity.status(200).body("Usuário foi removido com sucesso.");
    }

}
