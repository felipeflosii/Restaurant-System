package br.com.flosi.restaurant.services;

import br.com.flosi.restaurant.dtos.OrderDTO;
import br.com.flosi.restaurant.models.Dish;
import br.com.flosi.restaurant.models.Order;
import br.com.flosi.restaurant.models.OrderStatus;
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

    public Order save(OrderDTO dto) {
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
        return repository.save(order);
    }

    public List<Order> findAll() {
        return repository.findAll();
    }

    public Order findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public void delete (Long id) {
        repository.deleteById(id);
    }
}
