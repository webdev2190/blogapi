package com.blogapi.exceptions;

import com.blogapi.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice//If any exception Occurred in the controller layer then that exception will we handled by this class.
//TODO: Jab bhi koi exception aayega controller layer me to wo exceptions iss class me aayega. ye kerta hai @Controlleradvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    // In this class has no exception handler methods
    @ExceptionHandler(ResourceNotFoundException.class)//ExceptionHandler ke under hum ResourceNotFoundException.class ka name mention ker rhey hai.Agar is class me koi bhi exception aata hai to GlobalExceptionHandler class me aayega
    public ResponseEntity<ErrorDetails>
    handleResourceNotFoundException(ResourceNotFoundException exception,WebRequest webRequest){
        //TODO: WebRquest is a builtin class which helps us to send back some information to postman
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
                webRequest.getDescription(false)); //Todo here we have pass the 3 Parameters Date() and exception.getMessage() and  webRequest.getDescription().
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

}

//TODO: In the Interviewer ask how to handle a Exception sir In my Project I handel the exception by creating a class by @ControllerAdvisor annotation in that class
//TODO: Global exceptions are handled by the Java runtime, while specific exceptions are handled by the code that calls the method or class that throws the exception.