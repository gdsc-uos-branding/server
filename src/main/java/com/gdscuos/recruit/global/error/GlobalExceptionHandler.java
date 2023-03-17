package com.gdscuos.recruit.global.error;

import com.gdscuos.recruit.global.error.exception.AccessDeniedException;
import com.gdscuos.recruit.global.error.exception.BusinessException;
import com.gdscuos.recruit.global.error.exception.EntityNotFoundException;
import com.gdscuos.recruit.global.error.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<ErrorResponse> handleAccessDeniedException(
            final AccessDeniedException exception) {
        log.error("handleAccessDeniedException", exception);
        final ErrorCode errorCode = exception.getErrorCode();
        final ErrorResponse response = ErrorResponse.from(errorCode);
        return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatus()));
    }

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorResponse> handleBusinessException(
            final BusinessException exception) {
        log.error("handleBusinessException", exception);
        final ErrorCode errorCode = exception.getErrorCode();
        final ErrorResponse response = ErrorResponse.from(errorCode);
        return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatus()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleEntityNotFoundException(
            final EntityNotFoundException exception) {
        log.error("handleEntityNotFoundException", exception);
        final ErrorCode errorCode = exception.getErrorCode();
        final ErrorResponse response = ErrorResponse.from(exception.getErrorCode());
        return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatus()));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(
            final HttpRequestMethodNotSupportedException exception) {
        log.error("handleHttpRequestMethodNotSupportedException", exception);
        final ErrorCode errorCode = ErrorCode.METHOD_NOT_ALLOWED;
        final ErrorResponse response = ErrorResponse.from(errorCode);
        return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatus()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
            final MethodArgumentNotValidException exception) {
        log.error("handleMethodArgumentNotValidException", exception);
        final ErrorCode errorCode = ErrorCode.INVALID_INPUT_VALUE;
        ErrorResponse response = ErrorResponse.from(errorCode);
        return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatus()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(
            final HttpMessageNotReadableException exception) {
        log.error("handleHttpMessageNotReadableException", exception);
        final ErrorCode errorCode = ErrorCode.INVALID_INPUT_VALUE;
        ErrorResponse response = ErrorResponse.from(errorCode);
        return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatus()));
    }

    @ExceptionHandler(OAuth2AuthenticationException.class)
    protected ResponseEntity<ErrorResponse> handleOAuth2AuthenticationException(
            final OAuth2AuthenticationException exception) {
        log.error("handleOAuth2AuthenticationException", exception);
        final ErrorCode errorCode = ErrorCode.OAUTH2_AUTHENTICATION_FAILED;
        ErrorResponse response = ErrorResponse.from(errorCode);
        return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatus()));
    }
}
