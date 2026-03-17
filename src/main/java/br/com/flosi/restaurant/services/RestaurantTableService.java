package br.com.flosi.restaurant.services;

import br.com.flosi.restaurant.dtos.RestaurantTableDTO;
import br.com.flosi.restaurant.dtos.RestaurantTableResponseDTO;
import br.com.flosi.restaurant.exceptions.ResourceNotFoundException;
import br.com.flosi.restaurant.models.Restaurant;
import br.com.flosi.restaurant.models.RestaurantTable;
import br.com.flosi.restaurant.repositories.RestaurantRepository;
import br.com.flosi.restaurant.repositories.RestaurantTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RestaurantTableService {

    private final RestaurantTableRepository repository;
    private final RestaurantRepository restaurantRepository;

    public RestaurantTableResponseDTO save(RestaurantTableDTO dto) {
        RestaurantTable restaurantTable = new RestaurantTable();
        restaurantTable.setTableCapacity(dto.getTableCapacity());
        restaurantTable.setTableNumber(dto.getTableNumber());
        repository.save(restaurantTable);

        RestaurantTableResponseDTO response = new RestaurantTableResponseDTO();
        response.setId(restaurantTable.getId());
        response.setTableNumber(restaurantTable.getTableNumber());
        response.setTableCapacity(restaurantTable.getTableCapacity());
        return response;
    }

    public RestaurantTableResponseDTO saveByRestaurant(Long restaurantId, RestaurantTableDTO dto) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
            .orElseThrow(() -> new ResourceNotFoundException ("Restaurant", restaurantId));

        RestaurantTable restaurantTable = new RestaurantTable();
        restaurantTable.setTableCapacity(dto.getTableCapacity());
        restaurantTable.setTableNumber(dto.getTableNumber());
        restaurantTable.setRestaurant(restaurant);
        repository.save(restaurantTable);

        RestaurantTableResponseDTO response = new RestaurantTableResponseDTO();
        response.setId(restaurantTable.getId());
        response.setTableNumber(restaurantTable.getTableNumber());
        response.setTableCapacity(restaurantTable.getTableCapacity());
        return response;
    }

    public List<RestaurantTableResponseDTO> findAll() {
        return repository.findAll().stream()
            .map(restaurantTable -> {
                RestaurantTableResponseDTO response = new RestaurantTableResponseDTO();
                response.setId(restaurantTable.getId());
                response.setTableNumber(restaurantTable.getTableNumber());
                response.setTableCapacity(restaurantTable.getTableCapacity());
                return response;
            })
            .toList();
    }

    public RestaurantTableResponseDTO findById(Long id) {
        RestaurantTable restaurantTable = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("RestaurantTable", id));

        RestaurantTableResponseDTO response = new RestaurantTableResponseDTO();
        response.setId(restaurantTable.getId());
        response.setTableNumber(restaurantTable.getTableNumber());
        response.setTableCapacity(restaurantTable.getTableCapacity());
        return response;
    }

    public RestaurantTableResponseDTO update(Long id, RestaurantTableDTO dto) {
        RestaurantTable restaurantTable = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("RestaurantTable", id));
        restaurantTable.setTableCapacity(dto.getTableCapacity());
        restaurantTable.setTableNumber(dto.getTableNumber());
        repository.save(restaurantTable);

        RestaurantTableResponseDTO response = new RestaurantTableResponseDTO();
        response.setId(restaurantTable.getId());
        response.setTableNumber(restaurantTable.getTableNumber());
        response.setTableCapacity(restaurantTable.getTableCapacity());
        return response;
    }

    public void delete (Long id) {
        repository.deleteById(id);
    }

}
