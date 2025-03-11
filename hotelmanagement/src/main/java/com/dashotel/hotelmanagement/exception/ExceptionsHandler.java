package com.dashotel.hotelmanagement.exception;

import com.dashotel.hotelmanagement.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> exceptionHandler(Exception e) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(ErrorCode.UNCATEGORIZED_EXEPTION.getCode());
        apiResponse.setMessage(ErrorCode.UNCATEGORIZED_EXEPTION.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ApiResponse> accessDeniedException (AccessDeniedException e) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(ErrorCode.UNAUTHORIZED.getCode());
        apiResponse.setMessage(ErrorCode.UNAUTHORIZED.getMessage());

        return ResponseEntity.status(ErrorCode.UNAUTHORIZED.getStatusCode()).body(apiResponse);
    }

    @ExceptionHandler(value = CustomException.class)
    ResponseEntity<ApiResponse> AppRuntimeExceptionHandler(CustomException e) {
        ApiResponse apiResponse = new ApiResponse();

        ErrorCode errorCode = e.getErrorCode();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());

        return ResponseEntity.status(errorCode.getStatusCode()).body(apiResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> argumentNotValidExceptionHandler(MethodArgumentNotValidException e) {

        ApiResponse apiResponse = new ApiResponse();
        ErrorCode errorCode = ErrorCode.INVALID_KEY;

        try {
            errorCode = ErrorCode.valueOf(e.getFieldError().getDefaultMessage());

        } catch (IllegalArgumentException illegalArgumentException) {
            illegalArgumentException.printStackTrace();
        }

        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(
                errorCode.getMessage()
        );

        return ResponseEntity.badRequest().body(apiResponse);
    }
}
