package com.example.Miniproject.repository;

import com.example.Miniproject.entity.model.Houseowner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseOwnerRepository extends JpaRepository<Houseowner,String> {
    Houseowner findByHouse_IdAndUser_Username(String houseId, String username);
}
