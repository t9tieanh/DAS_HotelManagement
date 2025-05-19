package com.dashotel.hotelmanagement.service.promotion;

import com.dashotel.hotelmanagement.dto.common.DiscountDTO;
import com.dashotel.hotelmanagement.dto.response.common.CreationResponse;

import java.text.ParseException;
import java.util.List;

public interface IDiscountService {
    CreationResponse addDiscount(DiscountDTO request);
    List<DiscountDTO> getAvailableDiscount() throws ParseException;
}
