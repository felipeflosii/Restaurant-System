package br.com.flosi.restaurant.dtos;

import br.com.flosi.restaurant.models.RestaurantSpecialty;
import lombok.Data;

@Data
public class RestaurantResponseDTO {

    private Long id;
    private String name;
    private String address;
    private RestaurantSpecialty specialty;
}
