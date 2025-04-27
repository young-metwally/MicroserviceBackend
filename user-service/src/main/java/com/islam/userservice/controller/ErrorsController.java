package com.islam.userservice.controller;

import com.islam.userservice.exceptions.DataNotValidatedException;
import com.islam.userservice.exceptions.DuplicateEntryException;
import com.islam.userservice.exceptions.InstanceUndefinedException;
import com.islam.userservice.exceptions.SuspendedUserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.auth.login.CredentialNotFoundException;

@RestControllerAdvice
public class ErrorsController {

    @ExceptionHandler(DataNotValidatedException.class)
    public ResponseEntity<String> handleDataNotValidatedException(Error error) {
        return new ResponseEntity<>(error.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(InstanceUndefinedException.class)
    public ResponseEntity<String> handleInstanceUndefinedException(Error error) {
        return new ResponseEntity<>(error.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(DuplicateEntryException.class)
    public ResponseEntity<String> handleDuplicateEntryException(Error error) {
        return new ResponseEntity<>(error.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CredentialNotFoundException.class)
    public ResponseEntity<String> handleCredentialNotFoundException(CredentialNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SuspendedUserException.class)
    public ResponseEntity<String> handleSuspendedUserException(Error error) {
        return new ResponseEntity<>(error.getMessage(), HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
    }
}
