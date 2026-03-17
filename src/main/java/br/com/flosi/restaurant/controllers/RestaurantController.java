package br.com.flosi.restaurant.controllers;

import br.com.flosi.restaurant.dtos.*;
import br.com.flosi.restaurant.services.DishService;
import br.com.flosi.restaurant.services.RestaurantService;
import br.com.flosi.restaurant.services.RestaurantTableService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService service;
    private final RestaurantTableService restaurantTableService;
    private final DishService dishService;

    @PostMapping
    public ResponseEntity<RestaurantResponseDTO> create(@RequestBody @Valid RestaurantDTO dto) {
        return ResponseEntity.status(201).body(service.save(dto));
    }

    @GetMapping
    public ResponseEntity<List<RestaurantResponseDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantResponseDTO> update(@PathVariable Long id, @RequestBody @Valid RestaurantDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // HTTP restaurant - dishes
    @PostMapping("/{id}/dishes")
    public ResponseEntity<DishResponseDTO> createDish(@PathVariable Long id, @RequestBody @Valid DishDTO dto) {
        return ResponseEntity.status(201).body(dishService.saveByRestaurant(id, dto));
    }

    @GetMapping("/{id}/dishes")
    public ResponseEntity<List<DishResponseDTO>> getDishes(@PathVariable Long id) {
        return ResponseEntity.ok(service.findDishesByRestaurantId(id));
    }

    // HTTP restaurant - tables
    @PostMapping("/{id}/tables")
    public ResponseEntity<RestaurantTableResponseDTO> createRestaurantTable(@PathVariable Long id, @RequestBody @Valid RestaurantTableDTO dto) {
        return ResponseEntity.status(201).body(restaurantTableService.saveByRestaurant(id, dto));
    }

    @GetMapping("/{id}/tables")
    public ResponseEntity<List<RestaurantTableResponseDTO>> getRestaurantTables(@PathVariable Long id) {
        return ResponseEntity.ok(service.findRestaurantTablesByRestaurantId(id));
    }
}
