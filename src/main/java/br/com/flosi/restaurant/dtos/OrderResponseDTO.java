package br.com.flosi.restaurant.dtos;

import br.com.flosi.restaurant.models.enums.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponseDTO {

    private Long id;
    private String customerName;
    private OrderStatus status;
    private BigDecimal total;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<DishResponseDTO> dishes;
}
