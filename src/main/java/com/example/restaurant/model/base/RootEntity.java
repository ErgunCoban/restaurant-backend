package com.example.restaurant.model.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(value = JsonInclude.Include.NON_NULL)  //null alanları geriye dönme
public class RootEntity<T> {

    private Integer status;

    private T payload;

    private String errorMessage;

    public static <T> RootEntity<T> ok(T payload){
        RootEntity<T> rootEntity = new RootEntity<>();

        rootEntity.setStatus(200);  //success ise 200 kodu setlenir
        rootEntity.setPayload(payload);
        rootEntity.setErrorMessage(null);

        return rootEntity;
    }

    public static <T> RootEntity<T> error(String errorMessage){
        RootEntity<T> rootEntity = new RootEntity<>();

        rootEntity.setStatus(500);  //error ise 500 kodu setlenir
        rootEntity.setPayload(null);    //error olduğu için veri yok null setlenir
        rootEntity.setErrorMessage(errorMessage);

        return rootEntity;
    }

}
