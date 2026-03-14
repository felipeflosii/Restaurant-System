package br.com.flosi.restaurant.dtos;

import br.com.flosi.restaurant.models.RestaurantSpecialty;
import lombok.Getter;

@Getter
public class RestaurantResponseDTO {

    private String id;
    private String name;
    private String address;
    private RestaurantSpecialty specialty;
}
