package com.dashotel.hotelmanagement.service.service;

import com.dashotel.hotelmanagement.dto.request.service.HotelFacilityRequest;
import com.dashotel.hotelmanagement.dto.response.common.CreationResponse;

public interface IFacilityService {
    CreationResponse addHotelFacility (HotelFacilityRequest request);
}
