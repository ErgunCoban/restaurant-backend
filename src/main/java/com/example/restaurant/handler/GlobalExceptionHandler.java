package com.example.restaurant.handler;

import com.example.restaurant.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    //Base exception fırladığında devreye girecek olan metot
    @ExceptionHandler(value = {BaseException.class})
    public ResponseEntity<ApiError<?>> handleBaseException(BaseException exception, WebRequest request){
        return ResponseEntity.badRequest().body(createApiError(exception.getMessage(), request));
    }

    //@Valid ile işaretlenmiş bir request body validationdan geçemediğinde çalışır
    //Method argument not valid exception fırladığında devreye girecek olan metot
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ApiError<Map<String, List<String>>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, WebRequest request){
        Map<String, List<String>> map = new HashMap<>();
        //tüm hata nesnelerini dönüp listeye ekliyoruz
        for (ObjectError objectError : exception.getBindingResult().getAllErrors()){
            //get field ile username, email vb. yerlerden hatalar düşebilir
            String fieldName = ((FieldError) objectError).getField();

            //aynı fieldda birden fazla hata birikirse diye list tutup map ile ekleme yaptık
            if (map.containsKey(fieldName)){
                map.put(fieldName, addValue(map.get(fieldName), objectError.getDefaultMessage()));
            }else {
                map.put(fieldName, addValue(new ArrayList<>(), objectError.getDefaultMessage()));
            }
        }

        return ResponseEntity.badRequest().body(createApiError(map, request));
    }


    private List<String> addValue(List<String> list, String newValue){
        list.add(newValue);
        return list;
    }

    private String getHostName(){
        try{
            return Inet4Address.getLocalHost().getHostName();   //hatanın gerçekleştiği sunucuyu almak için
        }catch (UnknownHostException e){
            throw new RuntimeException(e);
        }
    }

    public <E> ApiError<E> createApiError(E message, WebRequest request){
        ApiError<E> apiError = new ApiError<>();
        apiError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        ExceptionResponse<E> exception = new ExceptionResponse<>();
        exception.setCreateTime(new Date());
        exception.setPath(request.getDescription(false).substring(4));  //endpointi alırız
        exception.setMessage(message);
        exception.setHostName(getHostName());

        apiError.setException(exception);
        return apiError;
    }

}
