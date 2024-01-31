package com.example.demo.utility;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.reactive.function.client.WebClient;

public class ExtApi {
    private static final WebClient.Builder builder = WebClient.builder();

    private ExtApi(){

    }

    /**
     * Retrieves JSON data from external API based on the provided address
     * @param address address for which coordinates need to be obtained
     * @return JsonNode containing the geocoding information
     */

    public static JsonNode getJSON(String address){
        return builder.build().get().uri(Mapper.getGeocodingURL(address)).retrieve().bodyToMono(JsonNode.class).block();
    }

    /**
     * Retrieves JSON data from external API based on the provided latitude and longitude
     * @param latitude latitude coordinate for which the address needs to be obtained
     * @param longitude longitude coordinate for which the address needs to be obtained
     * @return JsonNode containing the reverse geocoding information
     */
    public static JsonNode getJSON(double latitude, double longitude){
        return builder.build().get().uri(Mapper.getReverseGeocodingURL(latitude,longitude)).retrieve().bodyToMono(JsonNode.class).block();
    }
}
