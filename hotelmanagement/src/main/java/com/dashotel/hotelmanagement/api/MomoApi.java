package com.dashotel.hotelmanagement.api;

import com.dashotel.hotelmanagement.dto.request.common.CreateMonoRequest;
import com.dashotel.hotelmanagement.dto.response.common.CreateMonoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "momo", url = "${momo.end-point}")
public interface MomoApi {

    @PostMapping("/create")
    CreateMonoResponse createMomoQR(@RequestBody CreateMonoRequest createMonoRequest);
}
