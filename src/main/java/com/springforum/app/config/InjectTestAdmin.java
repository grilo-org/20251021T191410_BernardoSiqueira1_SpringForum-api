package com.springforum.app.config;

import com.springforum.app.modules.user.dtos.NewUserDTO;
import com.springforum.app.modules.user.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
public class InjectTestAdmin implements CommandLineRunner {
    @Autowired
    UserServices userServices;

    private NewUserDTO newUser =
            new NewUserDTO("superadmin123",
                       "forumadmin@email.com",
                    "administracao2025",
                    "none");

    @Override
    @Profile("testsession")
    public void run(String... args) throws Exception {

        try{
            userServices.createAdminUser(newUser);
            System.out.println("\n /////////////////Usuário de teste criado///////////////// \n");
            System.out.println(String.format("Login: %s | Senha: %s", newUser.userName(), newUser.userPassword()));
        }
        catch (Exception e){
            throw e;
        }

    }

}
