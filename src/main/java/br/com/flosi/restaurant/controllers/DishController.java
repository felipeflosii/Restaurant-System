package br.com.flosi.restaurant.controllers;

import br.com.flosi.restaurant.dtos.DishDTO;
import br.com.flosi.restaurant.dtos.DishResponseDTO;
import br.com.flosi.restaurant.services.DishService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/dishes")
public class DishController {

    private final DishService service;

    @PostMapping
    public ResponseEntity<DishResponseDTO> create(@RequestBody @Valid DishDTO dto) {
        return ResponseEntity.status(201).body(service.save(dto));
    }

    @GetMapping
    public ResponseEntity<List<DishResponseDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DishResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DishResponseDTO> update(@PathVariable Long id, @RequestBody @Valid DishDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}