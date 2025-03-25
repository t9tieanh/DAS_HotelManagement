package com.dashotel.hotelmanagement.enums;

public enum HotelFacilityCategory {
    PUBLIC_FACILITIES("Tiện nghi công cộng"),
    FOOD_AND_BEVERAGE("Ẩm thực"),
    BUSINESS_FACILITIES("Tiện nghi văn phòng"),
    GENERAL_FACILITIES("Tiện nghi chung"),
    INTERNET("Kết nối mạng"),
    HOTEL_SERVICES("Dịch vụ khách sạn"),
    TRANSPORTATION("Đưa đón");

    private final String description;

    HotelFacilityCategory(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
