package com.example.restaurant;

import com.example.restaurant.dto.request.CreateCategoryRequestDTO;
import com.example.restaurant.dto.request.CreateRestaurantTableRequestDTO;
import com.example.restaurant.dto.response.CategoryResponseDTO;
import com.example.restaurant.dto.response.RestaurantTableResponseDTO;
import com.example.restaurant.exception.BaseException;
import com.example.restaurant.exception.ErrorMessage;
import com.example.restaurant.exception.MessageType;
import com.example.restaurant.mapper.CategoryMapper;
import com.example.restaurant.mapper.RestaurantTableMapper;
import com.example.restaurant.model.Category;
import com.example.restaurant.model.RestaurantTable;
import com.example.restaurant.repository.CategoryRepository;
import com.example.restaurant.repository.RestaurantTableRepository;
import com.example.restaurant.rules.CategoryRules;
import com.example.restaurant.rules.RestaurantTableRules;
import com.example.restaurant.service.impl.CategoryServiceImpl;
import com.example.restaurant.service.impl.RestaurantTableServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryRules categoryRules;

    @Mock
    private CategoryMapper categoryMapper;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Test
    public void getById_WhenCategoryExists_ShouldReturnCategoryResponseDTO() {
        // Given
        Long categoryId = 1L;

        Category category = new Category();
        category.setId(categoryId);
        category.setName("Tatlılar");

        CategoryResponseDTO expectedDto = new CategoryResponseDTO();
        expectedDto.setId(categoryId);
        expectedDto.setName("Tatlılar");

        Mockito.doNothing().when(categoryRules).checkIfCategoryExistsById(categoryId);

        Mockito.when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));

        Mockito.when(categoryMapper.toResponseDTO(category)).thenReturn(expectedDto);

        CategoryResponseDTO result = categoryService.getCategoryById(categoryId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(categoryId, result.getId());
        Assertions.assertEquals("Tatlılar", result.getName());

        Mockito.verify(categoryRules, Mockito.times(1)).checkIfCategoryExistsById(categoryId);
        Mockito.verify(categoryRepository, Mockito.times(1)).findById(categoryId);
        Mockito.verify(categoryMapper, Mockito.times(1)).toResponseDTO(category);
    }

    @Test
    public void save_WhenCategoryAlreadyExists_ShouldThrowBaseException() {

        CreateCategoryRequestDTO requestDto = new CreateCategoryRequestDTO();
        requestDto.setName("Tatlılar");

        Mockito.doThrow(new BaseException(new ErrorMessage(MessageType.AlREADY_EXISTS, "Tatlılar")))
                .when(categoryRules).checkIfCategoryAlreadyExists("Tatlılar");

        // When & Then
        Assertions.assertThrows(BaseException.class, () -> {
            categoryService.saveCategory(requestDto);
        });

        Mockito.verify(categoryRules, Mockito.times(1)).checkIfCategoryAlreadyExists("Tatlılar");
        Mockito.verifyNoInteractions(categoryRepository);
        Mockito.verifyNoInteractions(categoryMapper);
    }
}