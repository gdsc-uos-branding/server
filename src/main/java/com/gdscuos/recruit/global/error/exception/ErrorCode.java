package com.gdscuos.recruit.global.error.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    // Common
    INVALID_INPUT_VALUE("C01", "Invalid Input Value.", HttpStatus.BAD_REQUEST.value()),
    METHOD_NOT_ALLOWED("C02", "Invalid Method Type.", HttpStatus.METHOD_NOT_ALLOWED.value()),
    ENTITY_NOT_FOUND("C03", "Entity Not Found.", HttpStatus.BAD_REQUEST.value()),
    INTERNAL_SERVER_ERROR("C04", "Internal Server Error.",
            HttpStatus.INTERNAL_SERVER_ERROR.value()),
    INVALID_TYPE_VALUE("C05", " Invalid Type Value.", HttpStatus.BAD_REQUEST.value()),

    // User
    USER_ACCESS_DENIED("U01", "User Access is Denied.", HttpStatus.UNAUTHORIZED.value()),
    USER_NOT_FOUND("U02", "User is not Found.", HttpStatus.BAD_REQUEST.value()),
    EMAIL_DUPLICATION("U03", "User Email is Duplicated.", HttpStatus.BAD_REQUEST.value()),

    // User - Token
    ACCESS_TOKEN_EXPIRED("AT01", "Access Token is Expired", HttpStatus.UNAUTHORIZED.value()),
    ACCESS_TOKEN_INVALID("AT02", "Access Token is Invalid.", HttpStatus.UNAUTHORIZED.value()),
    ACCESS_TOKEN_NOT_FOUND_IN_HEADER("AT03", "Access Token is not Found in Header.",
            HttpStatus.UNAUTHORIZED.value()),
    REFRESH_TOKEN_NOT_FOUND("AT04", "Refreshed Token is not Found.",
            HttpStatus.UNAUTHORIZED.value()),
    REFRESH_TOKEN_EXPIRED("AT05", "Refreshed Token is Expired.", HttpStatus.UNAUTHORIZED.value()),

    // Introduction
    INTRODUCTION_NOT_FOUND("I01", "Introduction is not Found.", HttpStatus.BAD_REQUEST.value()),

    // Application
    APPLICATION_QUESTION_NOT_FOUND("A01", "Application Question is not Found.",
            HttpStatus.BAD_REQUEST.value()),
    APPLICATION_NOT_FOUND("A02", "Application is not Found.", HttpStatus.BAD_REQUEST.value()),

    // External API
    EXTERNAL_API_FAILED("E01", "External API Request is failed.",
            HttpStatus.INTERNAL_SERVER_ERROR.value()),

    // Auth
    OAUTH2_AUTHENTICATION_FAILED("G01", "Google OAuth2 Authentication is failed",
            HttpStatus.INTERNAL_SERVER_ERROR.value());

    private final String code;
    private final String message;
    private int status;

    ErrorCode(String code, String message, int status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
