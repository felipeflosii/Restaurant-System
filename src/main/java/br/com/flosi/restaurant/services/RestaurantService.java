package br.com.flosi.restaurant.services;

import br.com.flosi.restaurant.dtos.DishResponseDTO;
import br.com.flosi.restaurant.dtos.RestaurantDTO;
import br.com.flosi.restaurant.dtos.RestaurantResponseDTO;
import br.com.flosi.restaurant.exceptions.ResourceNotFoundException;
import br.com.flosi.restaurant.models.Restaurant;
import br.com.flosi.restaurant.repositories.DishRepository;
import br.com.flosi.restaurant.repositories.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RestaurantService {

    private final RestaurantRepository repository;
    private final DishRepository dishRepository;

    public RestaurantResponseDTO save(RestaurantDTO dto) {
        Restaurant restaurant = new Restaurant();
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
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant", id));

        RestaurantResponseDTO response = new RestaurantResponseDTO();
        response.setId(restaurant.getId());
        response.setName(restaurant.getName());
        response.setAddress(restaurant.getAddress());
        response.setSpecialty(restaurant.getSpecialty());
        return response;
    }

    public RestaurantResponseDTO update(Long id, RestaurantDTO dto) {
        Restaurant restaurant = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant", id));
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

    public List<DishResponseDTO> findDishesByRestaurantId(Long restaurantId) {
        return dishRepository.findByRestaurantId(restaurantId).stream()
                .map(dish -> {
                    DishResponseDTO response = new DishResponseDTO();
                    response.setId(dish.getId());
                    response.setName(dish.getName());
                    response.setDescription(dish.getDescription());
                    response.setCategory(dish.getCategory());
                    response.setPrice(dish.getPrice());
                    return response;
                })
                .toList();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
