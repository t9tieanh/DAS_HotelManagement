package com.dashotel.hotelmanagement.enums;

public enum HotelImageEnum {
    LOBBY("Sảnh chờ"),
    OUTSIDE("Bên ngoài"),
    INSIDE("Bên trong");

    private final String description;

    HotelImageEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

