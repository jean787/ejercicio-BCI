package com.jherrell.msusers.Exception.advice;

import static com.jherrell.msusers.utils.Constants.MESSAGE_FIELDS_EMPTY;

import com.jherrell.msusers.Exception.RegexMatchException;
import com.jherrell.msusers.Exception.UserEmailExistException;
import com.jherrell.server_resource.model.api.ModelApiException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;


@RestControllerAdvice
public class ControllerAdvice {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserEmailExistException.class)
    public ModelApiException handleUserEmailExistException(
            UserEmailExistException ex, WebRequest request) {

        return ModelApiException.builder()
                .message(ex.getMessage()).build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RegexMatchException.class)
    public ModelApiException handleRegexNotMatchException(
            RegexMatchException ex, WebRequest request) {

        return ModelApiException.builder()
                .message(ex.getMessage()).build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ModelApiException handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        String message = ex.getBindingResult().getAllErrors()
                .stream().findFirst()
                .map(error -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    return "["+fieldName+"]" + " - " + errorMessage;
                })
                .orElse(MESSAGE_FIELDS_EMPTY);
        return ModelApiException.builder()
                .message(message).build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ModelApiException handleException(
            Exception ex, WebRequest request) {

        return ModelApiException.builder()
                .message(ex.getMessage()).build();
    }
}
