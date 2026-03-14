package br.com.flosi.restaurant.services;

import br.com.flosi.restaurant.dtos.DishDTO;
import br.com.flosi.restaurant.dtos.DishResponseDTO;
import br.com.flosi.restaurant.models.Dish;
import br.com.flosi.restaurant.repositories.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DishService {

    private final DishRepository repository;

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
                .orElseThrow(() -> new RuntimeException("Dish not found"));

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
                .orElseThrow(() -> new RuntimeException("Dish not found"));
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