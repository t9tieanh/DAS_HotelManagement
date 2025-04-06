package com.dashotel.hotelmanagement.enums;

public enum BookingStatusEnum {
    CREATED("Mới khởi tạo", 0),  // => Bước 1
    UNPAID("Chưa thanh toán", 1), // => Bước 2
    PAID("Đã thanh toán", 2),     // => Bước 3
    CANCELLED("Đã hủy", 3);       // => Bước 4

    private final String description;
    private final int step;  // Thêm thuộc tính step để lưu giá trị bước

    // Constructor
    BookingStatusEnum(String description, int step) {
        this.description = description;
        this.step = step;
    }

    // Getter cho mô tả
    public String getDescription() {
        return description;
    }

    // Getter cho giá trị bước
    public int getStep() {
        return step;
    }

    @Override
    public String toString() {
        return description;
    }
}

