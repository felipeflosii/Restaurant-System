package br.com.flosi.restaurant.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OrderDTO {

    @NotBlank (message = "Required client name")
    private String name;
}
