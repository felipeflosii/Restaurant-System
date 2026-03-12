package br.com.flosi.restaurant.services;

import br.com.flosi.restaurant.dtos.RestaurantDTO;
import br.com.flosi.restaurant.models.Restaurant;
import br.com.flosi.restaurant.repositories.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RestaurantService {

    private final RestaurantRepository repository;

    public Restaurant save(RestaurantDTO dto) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(dto.getName());
        restaurant.setAddress(dto.getAddress());
        restaurant.setSpecialty(dto.getSpecialty());
        return repository.save(restaurant);
    }

    public List<Restaurant> findAll() {
        return repository.findAll();
    }

    public Restaurant findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}