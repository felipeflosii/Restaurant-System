package br.com.flosi.restaurant.dtos;

import br.com.flosi.restaurant.models.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderResponseDTO {

    private Long id;
    private String customerName;
    private OrderStatus status;
    private BigDecimal total;

    private List<DishResponseDTO> dishes;
}
