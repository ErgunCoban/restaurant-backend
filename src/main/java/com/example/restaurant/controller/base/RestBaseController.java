package com.example.restaurant.controller.base;

import com.example.restaurant.model.base.RootEntity;

public class RestBaseController {

    public <T> RootEntity<T> ok(T payload){
        return RootEntity.ok(payload);
    }

    public <T> RootEntity<T> error(String errorMessage){
        return RootEntity.error(errorMessage);
    }

}
