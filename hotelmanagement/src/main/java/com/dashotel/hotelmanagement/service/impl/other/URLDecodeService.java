package com.dashotel.hotelmanagement.service.impl.other;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class URLDecodeService {
    public String decodeParams(String params) {
        return URLDecoder.decode(params, StandardCharsets.UTF_8);
    }

    public String[] extractParam(String decodedParams) {
        return decodedParams.split("&");
    }

    public String[] getKeyValue(String param) {
        return param.split("=");
    }
}
