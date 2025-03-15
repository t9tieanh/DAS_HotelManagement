package com.dashotel.hotelmanagement.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TokenEnum {
    RESFESH_TOKEN("reshfesh"),
    ACCESS_TOKEN("access"),
    SHORT_LIVED_TOKEN("short_lived"),
    ;

    @JsonValue
    public String getValue() {
        return name;
    }

    private final String name;

    TokenEnum(String name) {
        this.name = name;
    }
}
