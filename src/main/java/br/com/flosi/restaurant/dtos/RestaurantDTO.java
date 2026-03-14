package br.com.flosi.restaurant.dtos;

import br.com.flosi.restaurant.models.RestaurantSpecialty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RestaurantDTO {

    @NotBlank (message = "Required name")
    private String name;

    @NotBlank (message = "Required address")
    private String address;

    @NotBlank (message = "Required specialty")
    private RestaurantSpecialty specialty;
}
