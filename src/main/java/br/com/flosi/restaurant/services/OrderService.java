package br.com.flosi.restaurant.services;

import br.com.flosi.restaurant.dtos.DishResponseDTO;
import br.com.flosi.restaurant.dtos.OrderDTO;
import br.com.flosi.restaurant.dtos.OrderResponseDTO;
import br.com.flosi.restaurant.exceptions.ResourceNotFoundException;
import br.com.flosi.restaurant.models.Dish;
import br.com.flosi.restaurant.models.Order;
import br.com.flosi.restaurant.models.enums.OrderStatus;
import br.com.flosi.restaurant.repositories.DishRepository;
import br.com.flosi.restaurant.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository repository;
    private final DishRepository dishRepository;

    public OrderResponseDTO save(OrderDTO dto) {
        Order order = new Order();
        order.setCostumerName(dto.getName());
        order.setStatus(OrderStatus.CREATED);

        List<Dish> dishes = dishRepository.findAllById(dto.getDishIds());

        if (dishes.size() != dto.getDishIds().size()) {
            throw new RuntimeException("One or more dishes not found");
        }

        BigDecimal total = dishes.stream()
                .map(Dish::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotal(total);
        order.setDishes(dishes);
        repository.save(order);

        return toResponse(order);
    }

    public List<OrderResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public OrderResponseDTO findById(Long id) {
        Order order = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant", id));
        return toResponse(order);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private OrderResponseDTO toResponse(Order order) {
        OrderResponseDTO response = new OrderResponseDTO();
        response.setId(order.getId());
        response.setCustomerName(order.getCostumerName());
        response.setStatus(order.getStatus());
        response.setTotal(order.getTotal());

        List<DishResponseDTO> dishResponses = order.getDishes().stream()
                .map(dish -> {
                    DishResponseDTO dishResponse = new DishResponseDTO();
                    dishResponse.setId(dish.getId());
                    dishResponse.setName(dish.getName());
                    dishResponse.setDescription(dish.getDescription());
                    dishResponse.setCategory(dish.getCategory());
                    dishResponse.setPrice(dish.getPrice());
                    return dishResponse;
                })
                .toList();

        response.setDishes(dishResponses);
        return response;
    }
}
