package br.com.flosi.restaurant.dtos;

import br.com.flosi.restaurant.models.DishCategory;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DishResponseDTO {

    private Long id;
    private String name;
    private String description;
    private DishCategory category;
    private BigDecimal price;
}
