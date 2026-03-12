package br.com.flosi.restaurant.services;

import br.com.flosi.restaurant.dtos.OrderDTO;
import br.com.flosi.restaurant.models.Order;
import br.com.flosi.restaurant.models.OrderStatus;
import br.com.flosi.restaurant.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository repository;

    public Order save(OrderDTO dto) {
        Order order = new Order();
        order.setCostumerName(dto.getName());
        order.setStatus(OrderStatus.CREATED);
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
