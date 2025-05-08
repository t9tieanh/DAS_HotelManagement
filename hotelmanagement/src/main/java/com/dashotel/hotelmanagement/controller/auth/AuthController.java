package com.dashotel.hotelmanagement.controller.auth;

import com.dashotel.hotelmanagement.dto.request.common.AuthenticationRequest;
import com.dashotel.hotelmanagement.dto.request.common.CreationUserRequest;
import com.dashotel.hotelmanagement.dto.request.common.IntrospectRequest;
import com.dashotel.hotelmanagement.dto.response.ApiResponse;
import com.dashotel.hotelmanagement.dto.response.common.AuthenticationResponse;
import com.dashotel.hotelmanagement.dto.response.common.CreationUserResponse;
import com.dashotel.hotelmanagement.dto.response.common.IntrospectResponse;
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

    @PostMapping("outbound/authentication")
    ApiResponse<AuthenticationResponse> outBoundAuthentication(@RequestParam("code") String code) throws JOSEException {
        var result = authenticationService.outBoundAuthentication(code);
        return ApiResponse.<AuthenticationResponse>builder()
                .code(200)
                .result(result)
                .build();
    }

    @PostMapping(value="outbound/active-account", consumes = "multipart/form-data")
    public ApiResponse<Boolean> activeAccount(@ModelAttribute CreationUserRequest request) throws IOException {

        Boolean response = userService.activeAccount(request);

        String message = (response) ? "Account has been created" : "Account not be created";

        return ApiResponse.<Boolean>builder()
                .code(200)
                .message(message)
                .result(true)
                .build();
    }

    @PostMapping(value="/sign-up", consumes = "multipart/form-data")
    public ApiResponse<CreationUserResponse> signUp(@ModelAttribute CreationUserRequest request) throws IOException {

        CreationUserResponse response = userService.addUser(request);

        return ApiResponse.<CreationUserResponse>builder()
                .code(200)
                .message("Account has been created, please enter the OTP code sent to your email to activate account")
                .result(response)
                .build();
    }

    @PostMapping(value="/login")
    public ApiResponse<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) throws JOSEException, ParseException {

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
    public ApiResponse<Void> logOut() throws JOSEException, ParseException {

        authenticationService.logOut();

        return ApiResponse.<Void>builder()
                .code(200)
                .message("login successfully")
                .build();
    }

    // viết thêm cho android
    @GetMapping(value="/email-exists")
    public ApiResponse<Boolean> checkEmailExists(@RequestParam String email) {
        boolean exists = userService.emailExists(email);

        return ApiResponse.<Boolean>builder()
                .code(200)
                .result(exists)
                .message("login successfully")
                .build();
    }
}
