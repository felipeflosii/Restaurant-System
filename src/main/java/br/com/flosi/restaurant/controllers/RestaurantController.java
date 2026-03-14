package br.com.flosi.restaurant.controllers;

import br.com.flosi.restaurant.dtos.RestaurantDTO;
import br.com.flosi.restaurant.dtos.RestaurantResponseDTO;
import br.com.flosi.restaurant.services.RestaurantService;
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
}