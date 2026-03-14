package br.com.flosi.restaurant.services;

import br.com.flosi.restaurant.dtos.RestaurantDTO;
import br.com.flosi.restaurant.dtos.RestaurantResponseDTO;
import br.com.flosi.restaurant.models.Restaurant;
import br.com.flosi.restaurant.repositories.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RestaurantService {

    private final RestaurantRepository repository;

    public RestaurantResponseDTO save(RestaurantDTO dto) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(dto.getName());
        restaurant.setAddress(dto.getAddress());
        restaurant.setSpecialty(dto.getSpecialty());
        repository.save(restaurant); // salva sem retornar

        RestaurantResponseDTO response = new RestaurantResponseDTO();
        response.setId(restaurant.getId());
        response.setName(restaurant.getName());
        response.setAddress(restaurant.getAddress());
        response.setSpecialty(restaurant.getSpecialty());
        return response;
    }

    public List<RestaurantResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(restaurant -> {
                    RestaurantResponseDTO response = new RestaurantResponseDTO();
                    response.setId(restaurant.getId());
                    response.setName(restaurant.getName());
                    response.setAddress(restaurant.getAddress());
                    response.setSpecialty(restaurant.getSpecialty());
                    return response;
                })
                .toList();
    }

    public RestaurantResponseDTO findById(Long id) {
        Restaurant restaurant = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        RestaurantResponseDTO response = new RestaurantResponseDTO();
        response.setId(restaurant.getId());
        response.setName(restaurant.getName());
        response.setAddress(restaurant.getAddress());
        response.setSpecialty(restaurant.getSpecialty());
        return response;
    }

    public RestaurantResponseDTO update(Long id, RestaurantDTO dto) {
        Restaurant restaurant = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        restaurant.setName(dto.getName());
        restaurant.setAddress(dto.getAddress());
        restaurant.setSpecialty(dto.getSpecialty());
        repository.save(restaurant);

        RestaurantResponseDTO response = new RestaurantResponseDTO();
        response.setId(restaurant.getId());
        response.setName(restaurant.getName());
        response.setAddress(restaurant.getAddress());
        response.setSpecialty(restaurant.getSpecialty());
        return response;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}