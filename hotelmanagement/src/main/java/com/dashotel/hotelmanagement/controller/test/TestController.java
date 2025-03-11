package com.dashotel.hotelmanagement.controller.test;

import com.dashotel.hotelmanagement.dto.request.AuthenticationRequest;
import com.dashotel.hotelmanagement.dto.response.ApiResponse;
import com.dashotel.hotelmanagement.dto.response.AuthenticationResponse;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TestController {
    @GetMapping
    public ApiResponse<String> signUp() throws JOSEException {

        return ApiResponse.<String>builder()
                .code(200)
                .result("thua quá thua")
                .build();
    }
}
