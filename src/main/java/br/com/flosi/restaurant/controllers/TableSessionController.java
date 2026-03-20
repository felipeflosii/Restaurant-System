package br.com.flosi.restaurant.controllers;

import br.com.flosi.restaurant.dtos.TableSessionDTO;
import br.com.flosi.restaurant.dtos.TableSessionResponseDTO;
import br.com.flosi.restaurant.services.TableSessionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/table-sessions")
@RestController
public class TableSessionController {

    private final TableSessionService service;

    @PostMapping
    public ResponseEntity<TableSessionResponseDTO> create(@RequestBody @Valid TableSessionDTO dto) {
        return ResponseEntity.status(201).body(service.save(dto));
    }

    @GetMapping
    public ResponseEntity<List<TableSessionResponseDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TableSessionResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PatchMapping("/{id}/close")
    public ResponseEntity<TableSessionResponseDTO> close(@PathVariable Long id) {
        return ResponseEntity.ok(service.close(id));
    }
}
