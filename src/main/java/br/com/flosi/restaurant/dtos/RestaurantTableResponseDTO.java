package br.com.flosi.restaurant.dtos;

import lombok.Data;

@Data
public class RestaurantTableResponseDTO {

    private Long id;
    private Integer tableNumber;
    private Integer tableCapacity;
}
