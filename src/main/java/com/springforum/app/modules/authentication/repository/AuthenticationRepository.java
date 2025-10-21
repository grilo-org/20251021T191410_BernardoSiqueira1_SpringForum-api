package com.springforum.app.modules.authentication.repository;

import com.springforum.app.modules.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationRepository extends JpaRepository<User, Long> {

    UserDetails findByUserNickname(String userNickname);

}
