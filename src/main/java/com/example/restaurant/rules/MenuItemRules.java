package com.example.restaurant.rules;

import com.example.restaurant.exception.BaseException;
import com.example.restaurant.exception.ErrorMessage;
import com.example.restaurant.exception.MessageType;
import com.example.restaurant.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuItemRules {

    private final MenuItemRepository menuItemRepository;

    public void checkIfMenuItemExistsByName(String name){
        if (menuItemRepository.existsByName(name)){
            throw new BaseException(new ErrorMessage(MessageType.AlREADY_EXISTS, name));
        }
    }

    public void checkIfMenuItemExistsById(Long id){
        if (!menuItemRepository.existsById(id)){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXISTS, id.toString()));
        }
    }

}
