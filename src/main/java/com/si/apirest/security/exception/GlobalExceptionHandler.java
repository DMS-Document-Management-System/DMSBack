package com.si.apirest.security.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.si.apirest.exceptions.PersonExistException;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handlerArgumentException(IllegalArgumentException illegalArgumentException) {
        return new ResponseEntity<String>(illegalArgumentException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handlerRuntimeException(RuntimeException runtimeException) {
        return new ResponseEntity<String>(runtimeException.getMessage(), HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(PersonExistException.class)
    public ResponseEntity<?> personExistException(PersonExistException personExistException) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setStatusCode(HttpStatus.CONFLICT.value());
        errorDetails.setStatus(HttpStatus.CONFLICT.name());
        errorDetails.setErrorMessage(personExistException.getMessage());
        errorDetails.setTimeStamp(LocalDateTime.now());

        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodValidation(MethodArgumentNotValidException argumentValidation) {
        List<String> fieldErrors = argumentValidation.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("status", HttpStatus.BAD_REQUEST.name());
        errorMap.put("statusCode", HttpStatus.BAD_REQUEST.value());
        errorMap.put("timeStamp", LocalDate.now());
        errorMap.put("errMessages", fieldErrors);
        return new ResponseEntity<>(errorMap,HttpStatus.BAD_REQUEST);
    }


}
