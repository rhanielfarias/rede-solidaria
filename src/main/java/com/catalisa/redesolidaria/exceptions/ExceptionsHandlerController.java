package com.catalisa.redesolidaria.exceptions;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionsHandlerController {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(EntityNotFoundException e, HttpServletRequest servletRequest) {
        StandardError erro = new StandardError();
        erro.setTimestamp(Instant.now());
        erro.setStatus(HttpStatus.NOT_FOUND.value());
        erro.setError("Resource Not Found");
        erro.setMessage(e.getMessage());
        erro.setPath(servletRequest.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> exceptionHandlerEntradaInvalida(HttpMessageNotReadableException ex) {
        return new ResponseEntity<>("Compo digitado inválido", HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> exceptionHandlerContaNaoCadastrada(NoSuchElementException ex) {
        return new ResponseEntity<>("Id de conta não existente no banco de dados.", HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> exceptionHandlerBuscaPorTipo(MethodArgumentTypeMismatchException ex) {
        return new ResponseEntity<>("Filtro de busca não existente, favor informar um válido para realizar a busca.", HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<String> exceptionIdNaoExistente(EmptyResultDataAccessException ex) {
        return new ResponseEntity<>("Id de conta não existente no banco de dados.", HttpStatus.NOT_FOUND);
    }

}
