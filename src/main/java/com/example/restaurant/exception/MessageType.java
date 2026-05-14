package com.example.restaurant.exception;

import lombok.Getter;

@Getter
public enum MessageType {

    NO_RECORD_EXISTS("1001", "No record found"),
    AlREADY_EXISTS("1002","Record already exists"),
    USERNAME_NOT_FOUND("3001", "Username not found"),
    USERNAME_OR_PASSWORD_INCORRECT("3002", "Username or password is incorrect"),
    TOKEN_IS_EXPIRED("3003", "Token is expired"),
    REFRESH_TOKEN_NOT_FOUND("3004", "Refresh token not found"),
    REFESH_TOKEN_IS_EXPIRED("3005", "Refresh token is expired"),
    GENERAL_EXCEPTION("9999", "A general error occured");



    private String code;

    private String message;

    MessageType(String code, String message){
        this.code = code;
        this.message = message;
    }

}