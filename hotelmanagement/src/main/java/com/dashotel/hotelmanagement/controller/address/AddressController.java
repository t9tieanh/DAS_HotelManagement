package com.dashotel.hotelmanagement.controller.address;

import com.dashotel.hotelmanagement.dto.response.ApiResponse;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
public class AddressController {
    @GetMapping("/get-districts")
    ApiResponse<List<String>> getDistricts() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = getClass().getClassLoader().getResourceAsStream("districts.json");
            List<String> districts =  mapper.readValue(is, new TypeReference<List<String>>() {});
            return ApiResponse.<List<String>>builder()
                    .code(HttpStatus.OK.value())
                    .result(districts)
                    .build();
        } catch (StreamReadException e) {
            throw new RuntimeException(e);
        } catch (DatabindException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
