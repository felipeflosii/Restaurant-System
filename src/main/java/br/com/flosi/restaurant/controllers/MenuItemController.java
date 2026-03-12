package br.com.flosi.restaurant.controllers;

import br.com.flosi.restaurant.dtos.MenuItemDTO;
import br.com.flosi.restaurant.models.MenuItem;
import br.com.flosi.restaurant.services.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/menu-items")
public class MenuItemController {

    private final MenuItemService service;

    @PostMapping
    public ResponseEntity<MenuItem> create(@RequestBody MenuItemDTO dto) {
        return ResponseEntity.status(201).body(service.save(dto));
    }

    @GetMapping
    public ResponseEntity<List<MenuItem>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}