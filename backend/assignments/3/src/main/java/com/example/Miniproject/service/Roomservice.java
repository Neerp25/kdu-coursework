package com.example.Miniproject.service;

import com.example.Miniproject.Mapfunc.Mapper;
import com.example.Miniproject.dto.request.addrequest.AddDevicereqdto;
import com.example.Miniproject.dto.request.addrequest.AddRoomreqdto;
import com.example.Miniproject.dto.response.entityresponse.Roomresdto;
import com.example.Miniproject.entity.Userrole;
import com.example.Miniproject.entity.model.House;
import com.example.Miniproject.entity.model.Room;
import com.example.Miniproject.expection.Datanotmatchexp;
import com.example.Miniproject.expection.Entityunprocessexp;
import com.example.Miniproject.expection.InvalidAcess;
import com.example.Miniproject.repository.HouseOwnerRepository;
import com.example.Miniproject.repository.HouseRepository;
import com.example.Miniproject.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class Roomservice {
    private final RoomRepository roomRepository;
    private final HouseRepository houseRepository;
    private final Jwtservice jwtService;
    private final HouseOwnerRepository houseOwnerRepository;
    @Autowired
    public Roomservice(RoomRepository roomRepository, HouseRepository houseRepository,
                       Jwtservice jwtService, HouseOwnerRepository houseOwnerRepository) {
        this.roomRepository = roomRepository;
        this.houseRepository = houseRepository;
        this.jwtService = jwtService;
        this.houseOwnerRepository = houseOwnerRepository;
    }
    /**
     * Adds a room to a given house.
     */
    public Roomresdto addRoomToHouse(String houseId, AddRoomreqdto addRoomRequestDTO, String token) {
        try {
            String username = jwtService.extractUsername(token.substring(7));
            if (!(houseOwnerRepository.findByHouse_IdAndUser_Username(houseId, username)
                    .getRole().equals(Userrole.ADMIN))) {
                throw new InvalidAcess("User is not the admin");
            }
            House house = houseRepository.findById(houseId).orElse(null);
            if (Objects.isNull(house)) {
                throw new Datanotmatchexp("House does not exist");
            }
            Room room = Mapper.mapToRoom(house, addRoomRequestDTO);
            return Mapper.mapToRoomResponseDTO(roomRepository.save(room), "Room added to given house");
        } catch (InvalidAcess e) {
            throw new InvalidAcess("Room cannot be added. " + e.getMessage());
        } catch (Datanotmatchexp e) {
            throw new Datanotmatchexp("Room cannot be added. " + e.getMessage());
        } catch (Exception e) {
            throw new Entityunprocessexp("Room cannot be added");
        }
    }

}
