package br.com.flosi.restaurant.services;

import br.com.flosi.restaurant.dtos.DishDTO;
import br.com.flosi.restaurant.dtos.DishResponseDTO;
import br.com.flosi.restaurant.exceptions.ResourceNotFoundException;
import br.com.flosi.restaurant.models.Dish;
import br.com.flosi.restaurant.models.Restaurant;
import br.com.flosi.restaurant.repositories.DishRepository;
import br.com.flosi.restaurant.repositories.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DishService {

    private final DishRepository repository;
    private final RestaurantRepository restaurantRepository;

    public DishResponseDTO save(DishDTO dto) {
        Dish dish = new Dish();
        dish.setName(dto.getName());
        dish.setDescription(dto.getDescription());
        dish.setCategory(dto.getCategory());
        dish.setPrice(dto.getPrice());
        repository.save(dish);

        DishResponseDTO response = new DishResponseDTO();
        response.setId(dish.getId());
        response.setName(dish.getName());
        response.setDescription(dish.getDescription());
        response.setCategory(dish.getCategory());
        response.setPrice(dish.getPrice());
        return response;
    }

    public DishResponseDTO saveByRestaurant(Long restaurantId, DishDTO dto) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
            .orElseThrow(() -> new ResourceNotFoundException ("Restaurant", restaurantId));

        Dish dish = new Dish();
        dish.setName(dto.getName());
        dish.setDescription(dto.getDescription());
        dish.setCategory(dto.getCategory());
        dish.setPrice(dto.getPrice());
        dish.setRestaurant(restaurant);
        repository.save(dish);

        DishResponseDTO response = new DishResponseDTO();
        response.setId(dish.getId());
        response.setName(dish.getName());
        response.setDescription(dish.getDescription());
        response.setCategory(dish.getCategory());
        response.setPrice(dish.getPrice());
        return response;
    }

    public List<DishResponseDTO> findAll() {
        return repository.findAll().stream()
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

    public DishResponseDTO findById(Long id) {
        Dish dish = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dish", id));

        DishResponseDTO response = new DishResponseDTO();
        response.setId(dish.getId());
        response.setName(dish.getName());
        response.setDescription(dish.getDescription());
        response.setCategory(dish.getCategory());
        response.setPrice(dish.getPrice());
        return response;
    }

    public DishResponseDTO update(Long id, DishDTO dto) {
        Dish dish = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dish", id));
        dish.setName(dto.getName());
        dish.setDescription(dto.getDescription());
        dish.setCategory(dto.getCategory());
        dish.setPrice(dto.getPrice());
        repository.save(dish);

        DishResponseDTO response = new DishResponseDTO();
        response.setId(dish.getId());
        response.setName(dish.getName());
        response.setDescription(dish.getDescription());
        response.setCategory(dish.getCategory());
        response.setPrice(dish.getPrice());
        return response;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
