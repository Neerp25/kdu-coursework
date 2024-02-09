package com.example.Miniproject.service;

import com.example.Miniproject.Mapfunc.Mapper;
import com.example.Miniproject.dto.request.addrequest.AddDevicereqdto;
import com.example.Miniproject.dto.request.registerrequest.Deviceregisterreqdto;
import com.example.Miniproject.dto.response.entityresponse.Responsedto;
import com.example.Miniproject.entity.Userrole;
import com.example.Miniproject.entity.model.*;
import com.example.Miniproject.expection.Datanotmatchexp;
import com.example.Miniproject.expection.Entityunprocessexp;
import com.example.Miniproject.expection.InvalidAcess;
import com.example.Miniproject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class Deviceservice {
    private DeviceRepository deviceRepository;
    private HouseRepository houseRepository;
    private RoomRepository roomRepository;
    private InventoryRepository inventoryRepository;
    private Jwtservice jwtService;
    private UserRepository userRepository;
    private HouseOwnerRepository houseOwnerRepository;

    @Autowired
    public Deviceservice(DeviceRepository deviceRepository, HouseRepository houseRepository, RoomRepository roomRepository,
                         InventoryRepository inventoryRepository, Jwtservice jwtService, UserRepository userRepository,
                         HouseOwnerRepository houseOwnerRepository) {
        this.deviceRepository = deviceRepository;
        this.houseRepository = houseRepository;
        this.roomRepository = roomRepository;
        this.inventoryRepository = inventoryRepository;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.houseOwnerRepository = houseOwnerRepository;
    }

    /**
     * Adds a device to a house.
     */
    public Responsedto addDeviceToHouse(AddDevicereqdto addDeviceRequestDTO, String token) {
        try {
            String username = jwtService.extractUsername(token.substring(7));
            User user = userRepository.findById(username).orElse(null);
            if(Objects.isNull(user)){
                throw new InvalidAcess("Invalid username please check token");
            }
            House house = houseRepository.findById(addDeviceRequestDTO.getHouseId()).orElse(null);
            if(Objects.isNull(house)){
                throw new Datanotmatchexp("House with given id does not exist");
            }
            if(!(houseOwnerRepository.findByHouse_IdAndUser_Username(house.getId(),username).getRole().equals(Userrole.ADMIN))){
                throw new InvalidAcess("User is not the admin of the house");
            }
            Device device = deviceRepository.findById(addDeviceRequestDTO.getKickstonId()).orElse(null);
            if(Objects.isNull(device)){
                throw new Datanotmatchexp("Device with given id does not exist");
            }
            else {
                Room room = roomRepository.findById(addDeviceRequestDTO.getRoomId()).orElse(null);
                if(Objects.isNull(room)){
                    throw new Datanotmatchexp("Room with given id does not exist");
                }
                device.setHouse(house);
                device.setRoom(room);
                return Mapper.mapToResponseDTO(deviceRepository.save(device),"Device added to the given room of the house");
            }
        }
        catch (Datanotmatchexp e) {
            throw new Datanotmatchexp("Device cannot be added. " + e.getMessage());
        } catch (InvalidAcess e) {
            throw new InvalidAcess("Device cannot be added. " + e.getMessage());
        }
        catch (Exception e){
            throw new Entityunprocessexp("Device cannot be added to house please check again");
        }
    }
    /**
     * Registers a new device in System.
     */
    public Responsedto registerDevice(Deviceregisterreqdto deviceRegisterRequestDTO, String token) {
        try {
            String username = jwtService.extractUsername(token.substring(7));
            User user = userRepository.findById(username).orElse(null);
            if(Objects.isNull(user)){
                throw new InvalidAcess("Invalid username please check token");
            }
            Inventory inventory = inventoryRepository.findById(deviceRegisterRequestDTO.getKickstonId()).orElse(null);
            if(Objects.isNull(inventory)){
                throw new Datanotmatchexp("Device with given id does not exist in inventory");
            }
            if(!(inventory.getDevicePassword().equals(deviceRegisterRequestDTO.getDevicePassword()) && inventory.getDeviceUsername().equals(deviceRegisterRequestDTO.getDeviceUsername()))){
                throw new InvalidAcess("Invalid credentials please check again");
            }
            return Mapper.mapToResponseDTO(deviceRepository.save(Mapper.mapToDevice(deviceRegisterRequestDTO)),"Device registered successfully");
        }
        catch (Datanotmatchexp e) {
            throw new Datanotmatchexp("Device cannot be registered. " + e.getMessage());
        } catch (InvalidAcess e) {
            throw new InvalidAcess("Device cannot be registered. " + e.getMessage());
        } catch (Exception e){
            throw new Entityunprocessexp("Device cannot be registered please check again");
        }
    }

}
