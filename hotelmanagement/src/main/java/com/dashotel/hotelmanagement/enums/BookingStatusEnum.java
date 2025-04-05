package com.dashotel.hotelmanagement.enums;

public enum BookingStatusEnum {
    CREATED("Mới khởi tạo"), // => là trạng thái khách hàng mới ấn nút đặt hàng -> chưa chọn voucher, chưa nhập thông tin người ở
    UNPAID("Chưa thanh toán"), // => là trạng thái khách hàng đã hoàn thành nhập voucher và nhập thông tin người ở nhưng chưa thanh toans
    PAID("Đã thanh toán"), // => đã thanh toán, tương ứng với giao dịch đã thành công !
    CANCELLED("Đã hủy");

    private final String description;

    BookingStatusEnum(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
