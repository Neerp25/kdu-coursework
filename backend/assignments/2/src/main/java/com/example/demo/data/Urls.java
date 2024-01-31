package com.example.demo.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Urls {
    GEOCODING_URL("http://api.positionstack.com/v1/forward"),
    REVERSE_GEOCODING_URL("http://api.positionstack.com/v1/reverse"),
    API_KEY("bf28dccf711f98bfacd1d2a949bcd48f");

    final String value;
}
