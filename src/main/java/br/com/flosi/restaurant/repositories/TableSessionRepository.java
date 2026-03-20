package br.com.flosi.restaurant.repositories;

import br.com.flosi.restaurant.models.TableSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableSessionRepository extends JpaRepository<TableSession, Long> {
    List<TableSession> findByRestaurantTableId(Long restaurantTableId);
}
