package com.dashotel.hotelmanagement.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    INVALID_KEY(123,"Invalid request body", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1001,"Người dùng đã tồn tại !",HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1002,"User not found",HttpStatus.NOT_FOUND),
    WRONG_PASSWORD(1003,"Wrong password",HttpStatus.UNAUTHORIZED),
    UNCATEGORIZED_EXEPTION(999,"Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHORIZED(1006,"You do not have permission", HttpStatus.UNAUTHORIZED),
    USERNAME_INVALID(1004,"username must be at least 3 characters",HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1005,"password must be at least 8 characters",HttpStatus.BAD_REQUEST),
    INVALID_DOB(1008,"dob must least {min}",HttpStatus.BAD_REQUEST),
    UN_AUTHENTICATED(1009,"Unauthenticated",HttpStatus.UNAUTHORIZED),
    ACCOUNT_UN_ACTIVE(1009,"Tài khoản chưa được kích hoạt !",HttpStatus.UNAUTHORIZED),
    OTP_SEND(1010,"Cannot send otp",HttpStatus.INTERNAL_SERVER_ERROR),
    ROOM_NOT_FOUND(1012, "Không tìm thấy phòng", HttpStatus.NOT_FOUND),
    INVALID_FORMAT_JSON(1013, "Chuỗi json không hợp lệ !", HttpStatus.BAD_REQUEST),
    RESOURCE_NOT_FOUND(1014, "Không tìm thấy tài nguyên được yêu cầu", HttpStatus.NOT_FOUND),

    // dùng cho xác thực otp
    NOT_VERIFY_OTP (1011,"Otp code invalid, try again",HttpStatus.BAD_REQUEST),

    // lỗi phòng không còn trống
    ROOM_NOT_AVAILABLE(1013, "Phòng không còn trống trong khoảng thời gian yêu cầu", HttpStatus.NOT_FOUND),


    // hết thời gian giao dịch đặt phòng
    BOOKING_TIMEOUT(1015, "Giao dịch đặt phòng đã hết thời gian", HttpStatus.GONE),
    BOOKING_NOT_AVAILABLE(1015, "Giao dịch đặt phòng này không tồn tại", HttpStatus.NOT_FOUND),
    BOOKING_CANCELED(1015, "Bạn đã hủy giao dịch này", HttpStatus.NOT_FOUND),
    STEP_ALREADY_COMPLETED(1016, "Bước này đã được thực hiện, không thể thao tác lại", HttpStatus.CONFLICT),
    RESERVATION_CANNOT_CANCEL(2001, "Đặt phòng đã được xác nhận, không thể hủy!", HttpStatus.BAD_REQUEST),

    // dành cho dicount
    INSUFFICIENT_MEMBER_POINTS(123,"khách hàng không đủ điểm thành viên để áp dụng", HttpStatus.BAD_REQUEST),
    DISCOUNT_EXPIRED(123,"Mã giảm giá đã hết hạn hoặc chưa đến thời gian áp dụng.", HttpStatus.BAD_REQUEST),
    DISCOUNT_NOT_AVAILABLE(1014, "Mã giảm giá đã hết hiệu lực hoặc không tồn tại", HttpStatus.NOT_FOUND),
    DISCOUNT_NOT_APPLICABLE_FOR_BILL_TOTAL(1234, "Mã giảm giá không được áp dụng vì tổng tiền hóa đơn chưa đạt mức tối thiểu.", HttpStatus.BAD_REQUEST),

    ;
    private int code;
    private HttpStatusCode statusCode;
    private String message;


    ErrorCode (int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

}