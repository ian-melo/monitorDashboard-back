package br.com.monitor_dashboard.exception.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.monitor_dashboard.exception.ExceptionResponse;
import br.com.monitor_dashboard.exception.InvalidJwtAuthenticationException;
import br.com.monitor_dashboard.exception.ResourceNotFoundException;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception e, WebRequest request) {
    	return new ResponseEntity<>(defaultExceptionResponse(e, request), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleBadRequestexceptions(final Exception e, final WebRequest request) {
    	return new ResponseEntity<>(defaultExceptionResponse(e, request), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidJwtAuthenticationException.class)
    public final ResponseEntity<ExceptionResponse> invalidJwtAuthenticationExceptions(final Exception e, final WebRequest request) {
    	return new ResponseEntity<>(defaultExceptionResponse(e, request), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public final ResponseEntity<ExceptionResponse> authenticationExceptions(final Exception e, final WebRequest request) {
        return new ResponseEntity<>(defaultExceptionResponse(e, request), HttpStatus.NOT_FOUND);
    }
    
    private final ExceptionResponse defaultExceptionResponse(final Exception e, final WebRequest request) {
    	return new ExceptionResponse(
                new Date(),
                e.getMessage(),
                request.getDescription(false));
    }
    
}
