package br.com.flosi.restaurant.models;

import br.com.flosi.restaurant.models.enums.TableSessionStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "table_sessions")
public class TableSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDateTime openedAt;
    private LocalDateTime closedAt;

    @Enumerated(EnumType.STRING)
    private TableSessionStatus status;

    @ManyToOne
    @JoinColumn(name = "restaurant_table_id")
    private RestaurantTable restaurantTable;
}
