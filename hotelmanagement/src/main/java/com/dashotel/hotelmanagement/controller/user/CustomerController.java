package com.dashotel.hotelmanagement.controller.user;

import com.dashotel.hotelmanagement.dto.request.CustomerRequestDTO;
import com.dashotel.hotelmanagement.dto.response.ApiResponse;
import com.dashotel.hotelmanagement.dto.response.CustomerResponseDTO;
import com.dashotel.hotelmanagement.service.user.CustomerService;
import com.dashotel.hotelmanagement.utils.JwtUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerController {
    CustomerService customerService;
    JwtUtils jwtUtils;

    @GetMapping("/profile")
    public ApiResponse<CustomerResponseDTO> getCustomerProfile() {
        String customerId = jwtUtils.getCurrentUserId();
        CustomerResponseDTO customer = customerService.getCustomerById(customerId);

        String message = "Get profile successfully";

        return ApiResponse.<CustomerResponseDTO>builder()
                .code(HttpStatus.OK.value())
                .message(message)
                .result(customer)
                .build();
    }

    @PostMapping("/edit-profile")
    public ApiResponse<CustomerResponseDTO> editCustomerProfile(@ModelAttribute CustomerRequestDTO request) {
        String customerId = jwtUtils.getCurrentUserId();
        CustomerResponseDTO response = customerService.editProfile(customerId, request);

        String message = "Update profile successfully";

        return ApiResponse.<CustomerResponseDTO>builder()
                .code(HttpStatus.OK.value())
                .message(message)
                .result(response)
                .build();
    }
}
