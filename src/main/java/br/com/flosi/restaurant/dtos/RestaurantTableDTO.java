package br.com.flosi.restaurant.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RestaurantTableDTO {

    @NotNull(message = "Table number is required")
    private Integer tableNumber;

    @NotNull(message = "Table capacity is required")
    private Integer tableCapacity;
}
