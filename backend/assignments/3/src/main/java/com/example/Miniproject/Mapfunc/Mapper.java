package com.example.Miniproject.Mapfunc;

import com.example.Miniproject.dto.request.addrequest.AddRoomreqdto;
import com.example.Miniproject.dto.request.entityrequest.Inventoryreqdto;
import com.example.Miniproject.dto.request.registerrequest.Deviceregisterreqdto;
import com.example.Miniproject.dto.request.registerrequest.Houseregisterreqdto;
import com.example.Miniproject.dto.request.registerrequest.Userregisterreqdto;
import com.example.Miniproject.dto.response.entityresponse.Inventoryresdto;
import com.example.Miniproject.dto.response.entityresponse.Responsedto;
import com.example.Miniproject.dto.response.entityresponse.Roomresdto;
import com.example.Miniproject.dto.response.getresponse.Getallhouseresdto;
import com.example.Miniproject.dto.response.getresponse.Getroomdevresdto;
import com.example.Miniproject.dto.response.registerresponse.Houseregisterresdto;
import com.example.Miniproject.dto.response.registerresponse.Userregisterresdto;
import com.example.Miniproject.entity.Userrole;
import com.example.Miniproject.entity.model.*;
import org.springframework.http.HttpStatus;
import java.util.List;

public class Mapper {
    private Mapper() {
    }

    /**
     * Utility class containing static methods for mapping Data Transfer Objects (DTOs) to corresponding entities
     * within the application domain. Each method encapsulates the logic to transform DTOs into entity objects.
     * /
     /**
     * These methods aid in the conversion of incoming DTOs to entities for further processing and persistence.
     */
    public static Device mapToDevice(Deviceregisterreqdto deviceRegisterRequestDTO){
        return Device.builder()
                .kickstonId(deviceRegisterRequestDTO.getKickstonId())
                .deviceUsername(deviceRegisterRequestDTO.getDeviceUsername())
                .devicePassword(deviceRegisterRequestDTO.getDevicePassword())
                .build();
    }

    public static Responsedto mapToResponseDTO(Object object, String message) {
        return Responsedto.builder()
                .message(message)
                .object(object.toString())
                .httpStatus(HttpStatus.OK)
                .build();
    }

    public static House mapToHouse(Houseregisterreqdto houseRegisterRequestDTO){
        return House.builder()
                .address(houseRegisterRequestDTO.getAddress())
                .houseName(houseRegisterRequestDTO.getHouseName())
                .build();
    }

    public static Houseowner mapToHouseOwner(House house, User user, Userrole userRole) {
        return Houseowner.builder()
                .house(house)
                .user(user)
                .role(userRole)
                .build();
    }

    public static Room mapToRoom(House house, AddRoomreqdto addRoomRequestDTO) {
        return Room.builder()
                .roomName(addRoomRequestDTO.getRoomName())
                .house(house)
                .build();
    }

    public static User mapToUser( Userregisterreqdto userRegisterRequestDTO) {
        return User.builder()
                .username(userRegisterRequestDTO.getUsername())
                .password(userRegisterRequestDTO.getPassword())
                .name(userRegisterRequestDTO.getName())
                .firstName(userRegisterRequestDTO.getFirstName())
                .lastName(userRegisterRequestDTO.getLastName())
                .emailId(userRegisterRequestDTO.getEmailId())
                .build();
    }

    public static Inventory mapToInventory(Inventoryreqdto inventoryRequestDTO) {
        return Inventory.builder()
                .kickstonId(inventoryRequestDTO.getKickstonId())
                .deviceUsername(inventoryRequestDTO.getDeviceUsername())
                .devicePassword(inventoryRequestDTO.getDevicePassword())
                .manufactureDateTime(inventoryRequestDTO.getManufactureDateTime())
                .manufactureFactoryPlace(inventoryRequestDTO.getManufactureFactoryPlace())
                .build();
    }

    public static Houseregisterresdto mapToHouseRegisterResponseDTO(House house, String message) {
        return Houseregisterresdto.builder()
                .message(message)
                .house(house)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    public static Inventoryresdto mapToInventoryResponseDTO(List<Inventory> inventoryList) {
        return Inventoryresdto.builder()
                .inventory(inventoryList.toString())
                .httpStatus(HttpStatus.OK)
                .build();
    }

    public static Getallhouseresdto mapToGetAllHousesResponseDTO(List<House> houseList, String message) {
        return Getallhouseresdto .builder()
                .houses(houseList.toString())
                .message(message)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    public static Roomresdto mapToRoomResponseDTO(Room room, String message)  {
        return Roomresdto .builder()
                .message(message)
                .room(room)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    public static Getroomdevresdto mapToGetRoomsDevicesResponseDTO(List<Object> houseList, String message) {
        return Getroomdevresdto.builder()
                .message(message)
                .roomsAndDevices(houseList.toString())
                .httpStatus(HttpStatus.OK)
                .build();
    }
}
