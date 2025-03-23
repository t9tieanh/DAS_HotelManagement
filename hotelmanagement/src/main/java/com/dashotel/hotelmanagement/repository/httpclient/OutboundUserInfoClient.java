package com.dashotel.hotelmanagement.repository.httpclient;

import com.dashotel.hotelmanagement.dto.response.UserInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "outbound-user", url = "https://www.googleapis.com")
public interface OutboundUserInfoClient {
    @GetMapping(value = "oauth2/v1/userinfo")
    UserInfoResponse getUserInfo (@RequestParam("alt") String alt, @RequestParam("access_token") String accessToken);
}
