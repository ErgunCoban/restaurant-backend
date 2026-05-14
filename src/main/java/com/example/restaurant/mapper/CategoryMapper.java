package com.example.restaurant.mapper;

import com.example.restaurant.dto.request.CreateCategoryRequestDTO;
import com.example.restaurant.dto.response.CategoryResponseDTO;
import com.example.restaurant.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "id", ignore = true)
    Category toEntity(CreateCategoryRequestDTO createCategoryRequestDTO);


    CategoryResponseDTO toResponseDTO(Category category);

    List<CategoryResponseDTO> toResponseDTOList(List<Category> categories);

}
