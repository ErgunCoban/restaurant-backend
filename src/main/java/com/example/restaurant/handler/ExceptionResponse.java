package com.example.restaurant.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse<E> {

    //hatanın oluştuğu endpoint
    private String path;

    private Date createTime;

    //hatanın hangi sunucuda patladığı
    private String hostName;

    private E message;

}
