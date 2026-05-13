package com.example.restaurant.exception;

import lombok.Getter;

@Getter
public enum MessageType {

    NO_RECORD_EXISTS("1001", "No record found"),
    AlREADY_EXISTS("1002","Record already exists"),
    TOKEN_IS_EXPIRED("2001", "Token is expired"),
    USERNAME_NOT_FOUND("3001", "Username not found"),
    USERNAME_OR_PASSWORD_INCORRECT("3002", "Username or password is incorrect"),
    REFRESH_TOKEN_NOT_FOUND("3003", "Refresh token not found"),
    REFESH_TOKEN_IS_EXPIRED("1009", "Refresh token is expired"),
    GENERAL_EXCEPTION("9999", "A general error occured");



    private String code;

    private String message;

    MessageType(String code, String message){
        this.code = code;
        this.message = message;
    }

}
