package br.com.flosi.restaurant.services;

import br.com.flosi.restaurant.dtos.DishDTO;
import br.com.flosi.restaurant.models.Dish;
import br.com.flosi.restaurant.repositories.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DishService {

    private final DishRepository repository;

    public Dish save(DishDTO dto) {
        Dish item = new Dish();
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setCategory(dto.getCategory());
        item.setPrice(dto.getPrice());
        return repository.save(item);
    }

    public List<Dish> findAll() {
        return repository.findAll();
    }

    public Dish findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dishes not found"));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}