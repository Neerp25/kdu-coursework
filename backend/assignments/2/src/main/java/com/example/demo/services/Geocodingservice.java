package com.example.demo.services;

import com.example.demo.dto.Cordinatesdto;
import com.example.demo.exceptions.InvalidArgumentException;
import com.example.demo.utility.ExtApi;
import com.example.demo.utility.Mapper;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import com.example.demo.ConsoleLogger;
import org.springframework.stereotype.Service;


@Service
public class Geocodingservice {
    /**
     * Retrieves coordinates based on the provided address and caches it to improve performance
     */
    @Cacheable(key = "#address", value = "geocoding", unless = "#result.getRegion().equalsIgnoreCase(\"Goa\")")
    public Cordinatesdto getCoordinates(String address){
        JsonNode jsonNode = ExtApi.getJSON(address);
        if(jsonNode.get("data").isEmpty()){
            ConsoleLogger.errorMethod("Failed to fetch co-ordinates due to invalid address\n");
            throw new InvalidArgumentException("There are no co-ordinates available for " + address + " please check the address again");
        }
        Cordinatesdto coordinatesDTO = Mapper.getCoordinatesDTO(jsonNode);
        ConsoleLogger.infoMethod("Fetched co-ordinates from external API\n");

        return coordinatesDTO;
    }
}
