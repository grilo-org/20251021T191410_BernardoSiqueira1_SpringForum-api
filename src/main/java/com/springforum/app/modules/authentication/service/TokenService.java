package com.springforum.app.modules.authentication.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.springforum.app.modules.user.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class TokenService {

    @Value("{app.issuer.key}")
    private String issuerKey;

    public String generateToken(User user){

        try{
            Algorithm algorithm = Algorithm.HMAC256(issuerKey);

            String token = JWT.create()
                    .withIssuer(issuerKey)
                    .withSubject(user.getUserNickname())
                    .withExpiresAt(LocalDateTime.now().plusHours(12).toInstant(ZoneOffset.of("-03:00")))
                    .sign(algorithm);

            return token;
        }

        catch (JWTCreationException jwtCreationException){
            throw jwtCreationException;
        }

    }

    public String validateToken(String token) throws JWTDecodeException{

        try {
            Algorithm algorithm = Algorithm.HMAC256(issuerKey);

            String token_subject = JWT.require(algorithm)
                    .withIssuer(issuerKey)
                    .build()
                    .verify(token)
                    .getSubject();

            return token_subject;
        }

        catch (JWTDecodeException jwtDecodeException){
            throw jwtDecodeException;
        }

    }

}
