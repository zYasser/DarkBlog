package com.DarkBlog.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<ErrorMessage> EmailAlreadyExistException(EmailAlreadyExistException exception, WebRequest socket) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.CONFLICT, exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);

    }
    @ExceptionHandler(DoesNotExistException.class)
    public ResponseEntity<ErrorMessage> DoesNotExistException(DoesNotExistException exception, WebRequest socket) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);

    }
    @ExceptionHandler(UnauthenticatedException.class)
    public ResponseEntity<ErrorMessage> UnauthenticatedException(UnauthenticatedException exception, WebRequest socket) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.UNAUTHORIZED, exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);

    }
    @ExceptionHandler(PasswordMatchException.class)
    public ResponseEntity<ErrorMessage> PasswordMatchException(PasswordMatchException exception, WebRequest socket) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);

    }
    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<ErrorMessage> Unauthorized(AuthorizationException exception, WebRequest socket) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.FORBIDDEN, exception.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorMessage);

    }

}
