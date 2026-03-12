package br.com.flosi.restaurant.services;

import br.com.flosi.restaurant.dtos.MenuItemDTO;
import br.com.flosi.restaurant.models.MenuItem;
import br.com.flosi.restaurant.repositories.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MenuItemService {

    private final MenuItemRepository repository;

    public MenuItem save(MenuItemDTO dto) {
        MenuItem item = new MenuItem();
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setCategory(dto.getCategory());
        item.setPrice(dto.getPrice());
        return repository.save(item);
    }

    public List<MenuItem> findAll() {
        return repository.findAll();
    }

    public MenuItem findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}