package com.springforum.app.config.security;

import com.springforum.app.modules.authentication.repository.AuthenticationRepository;
import com.springforum.app.modules.authentication.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationRepository authenticationRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenSubject = this.verifyAuthentication(request);

        if (tokenSubject == null){
            filterChain.doFilter(request, response);
        }

        else{
            UserDetails userQuery = authenticationRepository.findByUserNickname(tokenSubject);
            var authenticationToken = new UsernamePasswordAuthenticationToken(userQuery, null, userQuery.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            filterChain.doFilter(request, response);
        }
    }

    private String verifyAuthentication(HttpServletRequest request){
        String userToken = request.getHeader("Authorization");

        if (userToken == null){
            return null;
        }

        String tokenSubject = tokenService.validateToken(userToken);

        return tokenSubject;
    }

}
