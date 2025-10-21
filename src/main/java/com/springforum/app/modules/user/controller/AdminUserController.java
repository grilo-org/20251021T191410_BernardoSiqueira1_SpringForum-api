package com.springforum.app.modules.user.controller;

import com.springforum.app.modules.user.dtos.EditUserCredentialsDTO;
import com.springforum.app.modules.user.services.AdminUserServices;
import com.springforum.app.modules.user.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/admin")
public class AdminUserController {

    @Autowired
    private UserServices userServices;

    @Autowired
    private AdminUserServices adminUserServices;

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/edit/{userId}")
    public ResponseEntity<?> editUserCredentials(@PathVariable Long userId, @RequestBody @Valid EditUserCredentialsDTO editCredentialsDTO){
        userServices.editUserCredentials(userId, editCredentialsDTO);

        return ResponseEntity.status(200).body("Usuário foi alterado com sucesso.");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/promote/{userId}")
    public ResponseEntity<?> promoteUserRole(@PathVariable Long userId){
        adminUserServices.promoteUserToAdmin(userId);

        return ResponseEntity.status(200).body("Usuário foi promovido para administrador.");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/suspend/{userId}")
    public ResponseEntity<?> suspendUser(@PathVariable Long userId){
        adminUserServices.suspendUser(userId);

        return ResponseEntity.status(200).body(String.format("Usuário id: %s  suspenso por tempo indeterminado.", userId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId){
        userServices.deleteUserById(userId);

        return ResponseEntity.status(200).body("O usuário foi deletado com sucesso.");
    }

}
