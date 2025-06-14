package com.dashotel.hotelmanagement.api.promotion;
import com.dashotel.hotelmanagement.dto.common.DiscountDTO;
import com.dashotel.hotelmanagement.dto.response.ApiResponse;
import com.dashotel.hotelmanagement.dto.response.common.CreationResponse;
import com.dashotel.hotelmanagement.service.impl.promotion.DiscountService;
import com.dashotel.hotelmanagement.service.promotion.IDiscountService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import java.text.ParseException;
import java.util.List;


@RestController
@RequestMapping("/promotion")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PromotionController {

    IDiscountService discountService;

    // lấy những discount có sawnx
    @GetMapping(value = "/available")
    ApiResponse<List<DiscountDTO>> getAvailableDiscount() throws ParseException {
        var result = discountService.getAvailableDiscount();
        return ApiResponse.<List<DiscountDTO>>builder()
                .code(200)
                .result(result)
                .build();
    }

    @PostMapping
    ApiResponse<CreationResponse> addPromotion(@RequestBody DiscountDTO request) {
        var result = discountService.addDiscount(request);
        return ApiResponse.<CreationResponse>builder()
                .code(200)
                .result(result)
                .build();
    }
}
