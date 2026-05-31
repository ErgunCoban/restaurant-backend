package com.example.restaurant;

import com.example.restaurant.dto.request.CreateRestaurantTableRequestDTO;
import com.example.restaurant.dto.response.RestaurantTableResponseDTO;
import com.example.restaurant.exception.BaseException;
import com.example.restaurant.exception.ErrorMessage;
import com.example.restaurant.exception.MessageType;
import com.example.restaurant.mapper.RestaurantTableMapper;
import com.example.restaurant.model.RestaurantTable;
import com.example.restaurant.repository.RestaurantTableRepository;
import com.example.restaurant.rules.RestaurantTableRules;
import com.example.restaurant.service.impl.RestaurantTableServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;



@ExtendWith(MockitoExtension.class)
public class RestaurantTableServiceTest {

    @Mock
    private RestaurantTableRepository restaurantTableRepository;

    @Mock
    private RestaurantTableRules restaurantTableRules;

    @Mock
    private RestaurantTableMapper restaurantTableMapper;

    @InjectMocks
    private RestaurantTableServiceImpl restaurantTableService;

    @Test
    public void getById_WhenRestaurantTableExists_ShouldReturnRestaurantTable() {
        Long tableId = 1L;

        RestaurantTable restaurantTable = new RestaurantTable();
        restaurantTable.setId(tableId);
        restaurantTable.setName("Masa-1");

        RestaurantTableResponseDTO expectedDto = new RestaurantTableResponseDTO();
        expectedDto.setId(tableId);
        expectedDto.setName("Masa-1");

        Mockito.when(restaurantTableRules.checkIfTableExistsById(tableId)).thenReturn(restaurantTable);

        Mockito.when(restaurantTableRepository.findById(tableId)).thenReturn(Optional.of(restaurantTable));

        Mockito.when(restaurantTableMapper.toResponse(restaurantTable)).thenReturn(expectedDto);

        RestaurantTableResponseDTO result = restaurantTableService.getRestaurantTableById(tableId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(tableId, result.getId());
        Assertions.assertEquals("Masa-1", result.getName());

        Mockito.verify(restaurantTableRules, Mockito.times(1)).checkIfTableExistsById(tableId);
        Mockito.verify(restaurantTableRepository, Mockito.times(1)).findById(tableId);
        Mockito.verify(restaurantTableMapper, Mockito.times(1)).toResponse(restaurantTable);
    }

    @Test
    public void save_WhenTableAlreadyExists_ShouldThrowBaseException() {
        CreateRestaurantTableRequestDTO requestDto = new CreateRestaurantTableRequestDTO();
        requestDto.setName("Masa-1");

        Mockito.doThrow(new BaseException(new ErrorMessage(MessageType.AlREADY_EXISTS, "Masa-1")))
                .when(restaurantTableRules).checkIfTableAlreadyExists("Masa-1");

        Assertions.assertThrows(BaseException.class, () -> {
            restaurantTableService.saveRestaurantTable(requestDto);
        });

        Mockito.verify(restaurantTableRules, Mockito.times(1)).checkIfTableAlreadyExists("Masa-1");
        Mockito.verifyNoInteractions(restaurantTableRepository);
        Mockito.verifyNoInteractions(restaurantTableMapper);
    }
}
