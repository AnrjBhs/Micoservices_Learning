package com.anuraj.inventoryservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anuraj.inventoryservice.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    


	List<Inventory> findBySkuCodeIn(List<String> skuCode);
}
