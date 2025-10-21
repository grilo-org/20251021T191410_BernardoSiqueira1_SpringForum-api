package com.springforum.app.config;

import com.auth0.jwt.exceptions.JWTDecodeException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.naming.AuthenticationException;
import java.util.List;

@Configuration
@ControllerAdvice
public class ExceptionHandlersConfig {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleInvalidParametersException(){
        return ResponseEntity.status(400).body("Erro nos parâmetros do caminho da requisição, verifique e tente novamente.");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException validationException){
        List<String> errorList = validationException.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        return ResponseEntity.status(400).body("Erro nos parâmetros do corpo da requisição: \n" + errorList);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(EntityNotFoundException notFoundException){
        return ResponseEntity.status(404).body("Informação buscada não encontrada na base de dados");
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> handleAuthenticationException(){
        return ResponseEntity.status(401).body("Não foi possível fazer o login, verifique suas credenciais");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleViolatedConstraint(DataIntegrityViolationException integrityViolationException){
        return ResponseEntity.status(409).body("Houveram conflitos nos valores: " + integrityViolationException.getMessage());
    }

    @ExceptionHandler(JWTDecodeException.class)
    public ResponseEntity<?> handleAuthorizationError(){
        return ResponseEntity.status(403).body("Usuário não está autorizado, verifique token/autenticação");
    }

}
