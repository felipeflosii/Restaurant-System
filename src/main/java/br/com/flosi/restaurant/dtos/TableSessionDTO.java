package br.com.flosi.restaurant.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TableSessionDTO {

    @NotNull(message = "Table is required")
    private Long restaurantTableId;
}
