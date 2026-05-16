package com.example.restaurant.rules;

import com.example.restaurant.exception.BaseException;
import com.example.restaurant.exception.ErrorMessage;
import com.example.restaurant.exception.MessageType;
import com.example.restaurant.model.RestaurantTable;
import com.example.restaurant.repository.RestaurantTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantTableRules {

    private final RestaurantTableRepository restaurantTableRepository;

     public void  checkIfTableAlreadyExists(String name){

        if(restaurantTableRepository.existsByName(name)){
            throw new BaseException(new ErrorMessage(MessageType.AlREADY_EXISTS,name));
        };
    }

    public RestaurantTable checkIfTableExistsById(Long id){

         Optional<RestaurantTable> optionalRestaurantTable = restaurantTableRepository.findById(id);

         if (optionalRestaurantTable.isEmpty()){
             throw new BaseException(
                     new ErrorMessage(MessageType.NO_RECORD_EXISTS, id.toString())
             );
         }else{
             return optionalRestaurantTable.get();
         }
    }
}
