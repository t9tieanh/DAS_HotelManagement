package com.dashotel.hotelmanagement.controller.payment;

import com.dashotel.hotelmanagement.dto.response.CreateMonoResponse;
import com.dashotel.hotelmanagement.service.payment.MomoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/momo")
public class MomoController {
    private final MomoService momoService;

    @PostMapping("/create")
    public CreateMonoResponse createQR() {
        return momoService.createQR();
    }
}
