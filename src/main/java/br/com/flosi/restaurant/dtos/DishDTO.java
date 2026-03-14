package br.com.flosi.restaurant.dtos;

import br.com.flosi.restaurant.models.enums.DishCategory;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class DishDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Category is required")
    private DishCategory category;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    private BigDecimal price;
}

// {
//    "name": "4 queijos",
//    "description": "Acmpanha queijo mussarela, cheedar, parmesão e gorgonzola.",
//    "category": "PIZZA",
//    "price": 70.00
//}