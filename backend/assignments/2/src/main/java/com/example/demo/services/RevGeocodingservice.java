package com.example.demo.services;

import com.example.demo.ConsoleLogger;
import com.example.demo.dto.Addressdto;
import com.example.demo.exceptions.InvalidArgumentException;
import com.example.demo.utility.ExtApi;
import com.example.demo.utility.Mapper;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service
public class RevGeocodingservice {
    /**
     * Retrieves address based on the provided latitude and longitude coordinates
     */
    @Cacheable(key = "{#latitude,#longitude}", value = "reverse-geocoding")
    public Addressdto getAddress(double latitude, double longitude){
        JsonNode jsonNode = ExtApi.getJSON(latitude,longitude);
        if(jsonNode.get("data").isEmpty()){
            ConsoleLogger.infoMethod("error");
            throw new InvalidArgumentException("Invalid parameter sent please check again\n");
        }
        Addressdto addressDTO = Mapper.getAddressDTO(jsonNode);
        ConsoleLogger.infoMethod("Fetched address from external API\n");
        return addressDTO;
    }
}
