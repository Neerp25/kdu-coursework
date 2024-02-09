package com.example.Miniproject.repository;

import com.example.Miniproject.entity.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device,String> {
    List<Device> findByHouse_Id(String houseId);
}
