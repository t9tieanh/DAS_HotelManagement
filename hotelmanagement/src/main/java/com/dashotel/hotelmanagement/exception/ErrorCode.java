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

    // dùng cho xác thực otp
    NOT_VERIFY_OTP (1011,"Otp code invalid, try again",HttpStatus.BAD_REQUEST),

    // lỗi phòng không còn trống
    ROOM_NOT_AVAILABLE(1013, "Phòng không còn trống trong khoảng thời gian yêu cầu", HttpStatus.NOT_FOUND),

    // lỗi hết discount
    DISCOUNT_NOT_AVAILABLE(1014, "Mã giảm giá đã hết hiệu lực", HttpStatus.NOT_FOUND),

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