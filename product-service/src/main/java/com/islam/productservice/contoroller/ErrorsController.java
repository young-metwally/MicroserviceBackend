package com.islam.productservice.contoroller;

import com.islam.productservice.exceptions.DataNotValidatedException;
import com.islam.productservice.exceptions.InstanceUndefinedException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorsController {

    @ExceptionHandler(DataNotValidatedException.class)
    public ResponseEntity<String> handleDataNotValidatedException(Error ex) {
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(InstanceUndefinedException.class)
    public ResponseEntity<String> handleInstanceUndefinedException(Error ex) {
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
    }



}
