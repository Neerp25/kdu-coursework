package com.example.Miniproject.service;

import com.example.Miniproject.Mapfunc.Mapper;
import com.example.Miniproject.dto.request.addrequest.AddUserreqdto;
import com.example.Miniproject.dto.request.modifyrequest.Updateadressreqdto;
import com.example.Miniproject.dto.request.registerrequest.Houseregisterreqdto;
import com.example.Miniproject.dto.response.entityresponse.Responsedto;
import com.example.Miniproject.dto.response.getresponse.Getallhouseresdto;
import com.example.Miniproject.dto.response.getresponse.Getroomdevresdto;
import com.example.Miniproject.dto.response.registerresponse.Houseregisterresdto;
import com.example.Miniproject.entity.Userrole;
import com.example.Miniproject.entity.model.*;
import com.example.Miniproject.expection.Datanotmatchexp;
import com.example.Miniproject.expection.Entityunprocessexp;
import com.example.Miniproject.expection.InvalidAcess;
import com.example.Miniproject.expection.NotFetchexp;
import com.example.Miniproject.repository.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.List;
@Service
public class Houseservice {
    private final HouseRepository houseRepository;
    private final HouseOwnerRepository houseOwnerRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final DeviceRepository deviceRepository;
    private final Jwtservice jwtService;

    String userNotExist = "User with given username does not exist";
    String houseNotExist = "House with given id does not exist";
    @Autowired
    public Houseservice(HouseRepository houseRepository, HouseOwnerRepository houseOwnerRepository,
                        UserRepository userRepository, RoomRepository roomRepository, DeviceRepository deviceRepository,
                        Jwtservice jwtService) {
        this.houseRepository = houseRepository;
        this.houseOwnerRepository = houseOwnerRepository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
        this.deviceRepository = deviceRepository;
        this.jwtService = jwtService;
    }

    /**
     * Adds a user to a house.
     */
    public Responsedto addUserToHouse(String houseId, AddUserreqdto addUserToHouseRequestDTO, String token) throws JsonProcessingException {
        try {
            User admin = userRepository.findById(jwtService.extractUsername(token.substring(7))).orElse(null);
            User user = userRepository.findById(addUserToHouseRequestDTO.getUsername()).orElse(null);
            if(Objects.isNull(admin) || Objects.isNull(user)){
                throw new Datanotmatchexp(userNotExist);
            }
            House house = houseRepository.findById(houseId).orElse(null);
            if(Objects.isNull(house)){
                throw new Datanotmatchexp(houseNotExist);
            }
            Houseowner houseOwner = houseOwnerRepository.findByHouse_IdAndUser_Username(houseId,admin.getUsername());
            if(!(Objects.isNull(houseOwner)) && houseOwner.getRole().equals(Userrole.ADMIN)){
                houseOwnerRepository.save(Mapper.mapToHouseOwner(house,user, Userrole.USER));
            }
            else {
                throw new InvalidAcess("User with given username is not admin of the house");
            }
            return Mapper.mapToResponseDTO(house,"User added to house successfully");
        }
        catch (Datanotmatchexp e) {
            throw new Datanotmatchexp("User cannot be added. " + e.getMessage());
        } catch (InvalidAcess e) {
            throw new InvalidAcess("User cannot be added. " + e.getMessage());
        } catch (Exception e) {
            throw new Entityunprocessexp("User cannot be added please check again");
        }
    }
    /**
     * Adds a new house.
     */
    public Houseregisterresdto addHouse(Houseregisterreqdto houseRegisterRequestDTO, String token) {
        try {
            User user = userRepository.findById(jwtService.extractUsername(token.substring(7))).orElse(null);
            if(Objects.isNull(user)){
                throw new Datanotmatchexp(userNotExist);
            }
            House house = houseRepository.save(Mapper.mapToHouse(houseRegisterRequestDTO));
            houseOwnerRepository.save(Mapper.mapToHouseOwner(house,user, Userrole.ADMIN));
            return Mapper.mapToHouseRegisterResponseDTO(house,"House added successfully");
        }
        catch (Datanotmatchexp e){
            throw new Datanotmatchexp("House cannot be added. " + e.getMessage());
        }
        catch (Exception e){
            throw new Entityunprocessexp("House cannot be added please check again");
        }
    }

    /**
     * Update the address of a house.
     */
    public Responsedto updateHouseAddress(String houseId, Updateadressreqdto houseDTO, String token) {
        try {
            User user = userRepository.findById(jwtService.extractUsername(token.substring(7))).orElse(null);
            if(Objects.isNull(user)){
                throw new Datanotmatchexp(userNotExist);
            }
            House house = houseRepository.findById(houseId).orElse(null);
            if(Objects.isNull(house)){
                throw new Datanotmatchexp(houseNotExist);
            }
            Houseowner houseOwner = houseOwnerRepository.findByHouse_IdAndUser_Username(houseId,user.getUsername());
            if(Objects.isNull(houseOwner)){
                throw new InvalidAcess("User with given username is not owner of the house");
            }
            house.setAddress(houseDTO.getAddress());
            house = houseRepository.save(house);
            return Mapper.mapToResponseDTO(house,"House address updated successfully");
        }
        catch (Datanotmatchexp e) {
            throw new Datanotmatchexp("House cannot be updated. " + e.getMessage());
        } catch (InvalidAcess e) {
            throw new InvalidAcess("House cannot be updated. " + e.getMessage());
        } catch (Exception e) {
            throw new Entityunprocessexp("House cannot be updated please check again");
        }
    }

    /**
     * Gets details of a house with its rooms and devices.
     */
    public Getroomdevresdto getHouseDetails(String houseId) {
        try {
            House house = houseRepository.findById(houseId).orElse(null);
            if(Objects.isNull(house)){
                throw new Datanotmatchexp(houseNotExist);
            }
            List<Room> roomList = roomRepository.findByHouse_Id(houseId);
            List<Device> deviceList = deviceRepository.findByHouse_Id(houseId);
            List<Object> houseList = new ArrayList<>();
            houseList.addAll(roomList);
            houseList.addAll(deviceList);
            return Mapper.mapToGetRoomsDevicesResponseDTO(houseList,"House details fetched successfully");

        }
        catch (Datanotmatchexp e) {
            throw new Datanotmatchexp("House details cannot be fetched. " + e.getMessage());
        } catch (Exception e){
            throw new Entityunprocessexp("House details cannot be fetched please check again");
        }
    }
    /**
     * Get a list of all houses.
     */
    public Getallhouseresdto getAllHouses() {
        try {
            List<House> houseList = houseRepository.findAll();
            return Mapper.mapToGetAllHousesResponseDTO(houseList,"House List fetched successfully");
        }
        catch (Exception e){
            throw new NotFetchexp("house list not fetch");
        }
    }


}
