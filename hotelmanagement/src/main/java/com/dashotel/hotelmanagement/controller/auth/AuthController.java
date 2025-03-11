package com.dashotel.hotelmanagement.controller.auth;

import com.dashotel.hotelmanagement.dto.request.AuthenticationRequest;
import com.dashotel.hotelmanagement.dto.request.CreationUserRequest;
import com.dashotel.hotelmanagement.dto.request.IntrospectRequest;
import com.dashotel.hotelmanagement.dto.request.LogoutRequest;
import com.dashotel.hotelmanagement.dto.response.ApiResponse;
import com.dashotel.hotelmanagement.dto.response.AuthenticationResponse;
import com.dashotel.hotelmanagement.dto.response.CreationUserResponse;
import com.dashotel.hotelmanagement.dto.response.IntrospectResponse;
import com.dashotel.hotelmanagement.service.auth.AuthenticationService;
import com.dashotel.hotelmanagement.service.user.UserService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {

    AuthenticationService authenticationService;
    UserService userService;

    @PostMapping(value="sign-up", consumes = "multipart/form-data")
    public ApiResponse<CreationUserResponse> signUp(@ModelAttribute CreationUserRequest request) throws IOException {

        CreationUserResponse response = userService.addUser(request);

        return ApiResponse.<CreationUserResponse>builder()
                .code(200)
                .message("Account has been created, please enter the OTP code sent to your email to activate account")
                .result(response)
                .build();
    }

    @PostMapping(value="login")
    public ApiResponse<AuthenticationResponse> signUp(@RequestBody AuthenticationRequest request) throws  JOSEException {

        AuthenticationResponse response = authenticationService.authentication(request);

        return ApiResponse.<AuthenticationResponse>builder()
                .code(200)
                .result(response)
                .build();
    }


    @PostMapping(value="introspect")
    public ApiResponse<IntrospectResponse> signUp(@RequestBody IntrospectRequest request) throws JOSEException, ParseException {

        boolean tokenValid = authenticationService.introspect(request.getToken());

        return ApiResponse.<IntrospectResponse>builder()
                .code(200)
                .result(new IntrospectResponse(tokenValid))
                .build();
    }

    @PostMapping(value="logout")
    public ApiResponse<Void> logOut(@RequestBody LogoutRequest request) throws JOSEException, ParseException {

        authenticationService.logOut(request);

        return ApiResponse.<Void>builder()
                .code(200)
                .message("login successly")
                .build();
    }
}
