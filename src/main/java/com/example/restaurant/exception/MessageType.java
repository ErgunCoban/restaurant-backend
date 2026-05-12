package com.example.restaurant.exception;

import lombok.Getter;

@Getter
public enum MessageType {

    NO_RECORD_EXISTS("1001", "No record found"),
    TOKEN_IS_EXPIRED("2001", "Token is expired"),
    GENERAL_EXCEPTION("9999", "A general error occured");


    private String code;

    private String message;

    MessageType(String code, String message){
        this.code = code;
        this.message = message;
    }

}
