package br.com.flosi.restaurant.dtos;

import br.com.flosi.restaurant.models.enums.TableSessionStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TableSessionResponseDTO {

    private Long id;
    private Integer tableNumber;
    private LocalDateTime openedAt;
    private LocalDateTime closedAt;
    private TableSessionStatus status;
    private Long restaurantTableId;
}
