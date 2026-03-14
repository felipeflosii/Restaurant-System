package br.com.flosi.restaurant.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {

    @NotBlank (message = "Required client name")
    private String name;

    private List<Long> menuItemIds;
}
