package com.springforum.app.modules.authentication.controller;

import com.springforum.app.modules.authentication.dtos.AuthCredentialsDTO;
import com.springforum.app.modules.authentication.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthCredentialsDTO authCredentialsDTO) {
        var response = authenticationService.authenticateUser(authCredentialsDTO.userLogin(), authCredentialsDTO.userPassword());

        return ResponseEntity.status(200).body(response);
    }

}
