package com.example.restaurant.exception;

import lombok.Getter;

@Getter
public enum MessageType {

    NO_RECORD_EXISTS("1001", "No record found"),
    AlREADY_EXISTS("1002","Record already exists"),
    TOKEN_IS_EXPIRED("2001", "Token is expired"),
    USERNAME_NOT_FOUND("3001", "Username not found"),
    GENERAL_EXCEPTION("9999", "A general error occured");



    private String code;

    private String message;

    MessageType(String code, String message){
        this.code = code;
        this.message = message;
    }

}