package com.stacksimplify.restservices.springboot_buildingblocks.exceptions;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

// with @ControllerAdvice this class is available to all the controller globally
@ControllerAdvice
// To make @RestControllerAdvice work from GlobalRestControllerAdviceExceptionHandler
// We need to comment @ControllerAdvice here!
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    //override MethodArgumentNotValidException which is handleMethodArgumentNotValid
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        //create an instance of our object CustomErrorDetails
        CustomErrorDetails errorDetails = new CustomErrorDetails(
                new Date(),
                "From MethodArgumentNotValidException in GlobalExceptionHandler",
                ex.getMessage());

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    //HttpRequestMethodNotSupportedException - This exception works when you use a wrong http method
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        //create an instance of our object CustomErrorDetails
        CustomErrorDetails errorDetails = new CustomErrorDetails(
                new Date(),
                "From HttpRequestMethodNotSupportedException in GlobalExceptionHandler",
                ex.getMessage());

        return new ResponseEntity<>(errorDetails, HttpStatus.METHOD_NOT_ALLOWED);
    }

    //UsernameNotFoundException
    @ExceptionHandler(UsernameNotFoundException.class)
    public final ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException ex, WebRequest request){
        //create an instance of our object CustomErrorDetails
        CustomErrorDetails customErrorDetails = new CustomErrorDetails(
                new Date(),
                ex.getMessage(),request.getDescription(false));

        return new ResponseEntity<>(customErrorDetails, HttpStatus.NOT_FOUND);

    }

    //ConstraintViolationException
    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request){
        //create an instance of our object CustomErrorDetails
        CustomErrorDetails customErrorDetails = new CustomErrorDetails(
                new Date(),
                ex.getMessage(),request.getDescription(false));

        return new ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);

    }

}
