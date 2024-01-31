package com.example.demo.utility;

import com.example.demo.data.Urls;
import com.example.demo.dto.Addressdto;
import com.example.demo.dto.Cordinatesdto;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.util.UriComponentsBuilder;

public class Mapper {
    private static final String GEOCODING_URL = Urls.GEOCODING_URL.getValue();
    private static final String REVERSE_GEOCODING_URL = Urls.REVERSE_GEOCODING_URL.getValue();
    private static final String API_KEY = Urls.API_KEY.getValue();
    /**
     * Converts JsonNode containing geocoding information to CoordinatesDTO
     * @param jsonNode JsonNode containing geocoding information
     * @return CoordinatesDTO object representing latitude, longitude, and region
     */
    public static Cordinatesdto getCoordinatesDTO(JsonNode jsonNode){
        JsonNode jsonNode1 = jsonNode.get("data").get(0);
        return new Cordinatesdto(jsonNode1.get("latitude").asDouble(),jsonNode1.get("longitude").asDouble(),jsonNode1.get("region").asText());
    }

    /**
     * Converts JsonNode containing reverse geocoding information to AddressDTO
     * @param jsonNode JsonNode containing reverse geocoding information
     * @return AddressDTO object representing address information
     */
    public static Addressdto getAddressDTO(JsonNode jsonNode){
        JsonNode jsonNode1 = jsonNode.get("data").get(0);
        StringBuilder addressStringBuilder = new StringBuilder();

        appendIfNotNull(addressStringBuilder, jsonNode1.get("name"));
        appendIfNotNull(addressStringBuilder, jsonNode1.get("street"));
        appendIfNotNull(addressStringBuilder, jsonNode1.get("locality"));
        appendIfNotNull(addressStringBuilder, jsonNode1.get("postal_code"));
        appendIfNotNull(addressStringBuilder, jsonNode1.get("region"));
        appendIfNotNull(addressStringBuilder, jsonNode1.get("country"));

        return new Addressdto(addressStringBuilder.toString());
    }
    private static void appendIfNotNull(StringBuilder builder, JsonNode valueNode) {
        if (valueNode != null && !valueNode.isNull()) {
            String value = valueNode.asText();
            if (value != null && !value.equalsIgnoreCase("null")) {
                if (!builder.isEmpty()) {
                    builder.append(", ");
                }
                builder.append(value);
            }
        }
    }

    /**
     * Constructs geocoding API URL based on the provided address
     * @param address address for which the geocoding URL needs to be constructed
     * @return geocoding API URL
     */
    public static String getGeocodingURL(String address){
        return UriComponentsBuilder.fromUriString(GEOCODING_URL).
                queryParam("access_key",API_KEY).queryParam("query",address).build().toUriString();
    }

    /**
     * Constructs reverse geocoding API URL based on the provided latitude and longitude
     * @param latitude latitude coordinate for which the reverse geocoding URL needs to be constructed
     * @param longitude longitude coordinate for which the reverse geocoding URL needs to be constructed
     * @return reverse geocoding API URL
     */
    public static String getReverseGeocodingURL(double latitude, double longitude){
        String query = latitude + "," + longitude;
        return UriComponentsBuilder.fromUriString(REVERSE_GEOCODING_URL).
                queryParam("access_key",API_KEY).queryParam("query",query).build().toUriString();
    }
}
