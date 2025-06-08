package com.dashotel.hotelmanagement.api.user;

import com.dashotel.hotelmanagement.dto.request.common.CustomerRequestDTO;
import com.dashotel.hotelmanagement.dto.response.ApiResponse;
import com.dashotel.hotelmanagement.dto.response.common.CreationResponse;
import com.dashotel.hotelmanagement.dto.response.common.CustomerResponseDTO;
import com.dashotel.hotelmanagement.service.impl.auth.AuthenticationService;
import com.dashotel.hotelmanagement.service.impl.user.CustomerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerController {
    CustomerService customerService;
    AuthenticationService authService;

    @GetMapping("/profile")
    public ApiResponse<CustomerResponseDTO> getCustomerProfile() {
        String customerId = authService.getCurrentUserId();
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
        String customerId = authService.getCurrentUserId();
        CustomerResponseDTO response = customerService.editProfile(customerId, request);

        String message = "Chỉnh sửa profile khách hàng thành công !";

        return ApiResponse.<CustomerResponseDTO>builder()
                .code(HttpStatus.OK.value())
                .message(message)
                .result(response)
                .build();
    }


    @PutMapping(
            path = "/update-user-image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ApiResponse<CreationResponse> updateImage(
            @RequestParam("file") MultipartFile file
    ) throws ParseException, IOException {
        CreationResponse response = customerService.updateImage(file);

        return ApiResponse.<CreationResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Đổi ảnh đại diện thành công !")
                .result(response)
                .build();
    }
}
