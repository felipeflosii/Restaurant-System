package br.com.flosi.restaurant.controllers;

import br.com.flosi.restaurant.dtos.RestaurantTableDTO;
import br.com.flosi.restaurant.dtos.RestaurantTableResponseDTO;
import br.com.flosi.restaurant.services.RestaurantTableService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/restaurant_tables")
@RestController
public class RestaurantTableController {

    private final RestaurantTableService service;

    @PostMapping
    public ResponseEntity<RestaurantTableResponseDTO> create(@RequestBody @Valid RestaurantTableDTO dto) {
        return ResponseEntity.status(201).body(service.save(dto));
    }

    @GetMapping
    public ResponseEntity<List<RestaurantTableResponseDTO>> getAll() {
        return  ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantTableResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantTableResponseDTO> update(@PathVariable Long id, @RequestBody @Valid RestaurantTableDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
