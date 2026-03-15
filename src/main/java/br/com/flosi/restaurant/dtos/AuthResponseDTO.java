package br.com.flosi.restaurant.dtos;

import br.com.flosi.restaurant.models.enums.UserRole;
import lombok.Data;

@Data
public class AuthResponseDTO {

    private String token;
    private String name;
    private UserRole role;
}
