package com.example.Miniproject.repository;

import com.example.Miniproject.entity.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,String> {
}
