package com.dashotel.hotelmanagement.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class CustomException extends RuntimeException {
    ErrorCode errorCode;
}
