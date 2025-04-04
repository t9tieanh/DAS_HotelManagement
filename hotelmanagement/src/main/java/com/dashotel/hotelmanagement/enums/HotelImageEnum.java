package com.dashotel.hotelmanagement.enums;

import com.dashotel.hotelmanagement.exception.CustomException;
import com.dashotel.hotelmanagement.exception.ErrorCode;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum HotelImageEnum {
    LOBBY("Sảnh chờ"),
    OUTSIDE("Bên ngoài"),
    INSIDE("Bên trong"),
    AVARTAR("Hình đại diện");

    private final String description;

    HotelImageEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @JsonValue
    public String getValue() {
        return description;
    }

    @JsonCreator
    public static HotelImageEnum fromValue(String value) {
        return Arrays.stream(HotelImageEnum.values())
                .filter(e -> e.description.equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }
}

