package br.com.flosi.restaurant.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "restaurant_tables")
public class RestaurantTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer tableNumber;
    private Integer tableCapacity;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

}
