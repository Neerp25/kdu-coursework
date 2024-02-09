package com.example.Miniproject.service;

import com.example.Miniproject.Mapfunc.Mapper;
import com.example.Miniproject.dto.request.entityrequest.Inventoryreqdto;
import com.example.Miniproject.dto.response.entityresponse.Inventoryresdto;
import com.example.Miniproject.dto.response.entityresponse.Responsedto;
import com.example.Miniproject.entity.model.Inventory;
import com.example.Miniproject.expection.Entityunprocessexp;
import com.example.Miniproject.expection.NotFetchexp;
import com.example.Miniproject.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class Inventoryservice {
    private InventoryRepository inventoryRepository;

    @Autowired
    public Inventoryservice(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }


    /**
     * Adds a new item to the inventory.
     */
    public Responsedto addInventoryItem(Inventoryreqdto inventoryRequestDTO) {
        try {
            return Mapper.mapToResponseDTO(
                    inventoryRepository.save(Mapper.mapToInventory(inventoryRequestDTO)),
                    "Item added to Inventory successfully"
            );
        } catch (Exception e) {
            throw new Entityunprocessexp("Not Processable");
        }
    }
    /**
     * Gets a list of all Items in a inventory.
     */
    public Inventoryresdto getInventoryItems() {
        try {
            List<Inventory> inventoryList = inventoryRepository.findAll();
            return Mapper.mapToInventoryResponseDTO(inventoryList);
        } catch (Exception e) {
            throw new NotFetchexp("inventory list Not Fetch");
        }
    }

}
