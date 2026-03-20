package br.com.flosi.restaurant.services;

import br.com.flosi.restaurant.dtos.TableSessionDTO;
import br.com.flosi.restaurant.dtos.TableSessionResponseDTO;
import br.com.flosi.restaurant.exceptions.ResourceNotFoundException;
import br.com.flosi.restaurant.models.RestaurantTable;
import br.com.flosi.restaurant.models.TableSession;
import br.com.flosi.restaurant.models.enums.TableSessionStatus;
import br.com.flosi.restaurant.repositories.RestaurantTableRepository;
import br.com.flosi.restaurant.repositories.TableSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TableSessionService {

    private final TableSessionRepository repository;
    private final RestaurantTableRepository restaurantTableRepository;

    public TableSessionResponseDTO save(TableSessionDTO dto) {
        RestaurantTable restaurantTable = restaurantTableRepository.findById(dto.getRestaurantTableId())
            .orElseThrow(() -> new ResourceNotFoundException("RestaurantTable", dto.getRestaurantTableId()));

        TableSession tableSession = new TableSession();
        tableSession.setRestaurantTable(restaurantTable);
        tableSession.setStatus(TableSessionStatus.OPEN);
        repository.save(tableSession);

        return toResponse(tableSession);
    }

    public List<TableSessionResponseDTO> findAll() {
        return repository.findAll().stream()
            .map(this::toResponse)
            .toList();
    }

    public TableSessionResponseDTO findById(Long id) {
        TableSession tableSession = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("TableSession", id));
        return toResponse(tableSession);
    }

    public TableSessionResponseDTO close(Long id) {
        TableSession tableSession = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("TableSession", id));
        tableSession.setStatus(TableSessionStatus.CLOSED);
        tableSession.setClosedAt(LocalDateTime.now());
        repository.save(tableSession);
        return toResponse(tableSession);
    }

    private TableSessionResponseDTO toResponse(TableSession tableSession) {
        TableSessionResponseDTO response = new TableSessionResponseDTO();
        response.setId(tableSession.getId());
        response.setOpenedAt(tableSession.getOpenedAt());
        response.setClosedAt(tableSession.getClosedAt());
        response.setStatus(tableSession.getStatus());
        response.setRestaurantTableId(tableSession.getRestaurantTable().getId());
        response.setTableNumber(tableSession.getRestaurantTable().getTableNumber());
        return response;
    }
}
