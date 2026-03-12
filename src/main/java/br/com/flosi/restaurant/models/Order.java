package br.com.flosi.restaurant.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String costumerName;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}