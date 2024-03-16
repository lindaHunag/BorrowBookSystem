package com.example.demo.repository;

import com.example.demo.model.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    Inventory findByISBN(String isbn);

    Inventory findByInventoryId(int inventoryId);
}
