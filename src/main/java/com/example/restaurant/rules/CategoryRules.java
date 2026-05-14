package com.example.restaurant.rules;

import com.example.restaurant.exception.BaseException;
import com.example.restaurant.exception.ErrorMessage;
import com.example.restaurant.exception.MessageType;
import com.example.restaurant.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryRules {

    private final CategoryRepository categoryRepository;

    public void checkIfCategoryAlreadyExists(String name) {

        if (categoryRepository.existsByName(name)) {
            throw new BaseException(new ErrorMessage(MessageType.AlREADY_EXISTS, name));
        }
    }
    public void checkIfCategoryExistsById(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXISTS, id.toString()));
        }
    }
}