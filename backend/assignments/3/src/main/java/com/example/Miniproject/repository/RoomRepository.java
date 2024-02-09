package com.example.Miniproject.repository;

import com.example.Miniproject.entity.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room,String> {

    List<Room> findByHouse_Id(String id);
}
