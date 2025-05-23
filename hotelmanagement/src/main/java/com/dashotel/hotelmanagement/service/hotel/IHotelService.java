package com.dashotel.hotelmanagement.service.hotel;

import com.dashotel.hotelmanagement.dto.request.hotel.HotelCreationRequest;
import com.dashotel.hotelmanagement.dto.request.hotel.HotelImageRequest;
import com.dashotel.hotelmanagement.dto.response.common.CreationResponse;
import com.dashotel.hotelmanagement.dto.response.hotel.HotelDestailResponse;
import com.dashotel.hotelmanagement.dto.response.hotel.HotelImageResponse;
import com.dashotel.hotelmanagement.dto.response.hotel.HotelImageTypeCountReponse;
import com.dashotel.hotelmanagement.dto.response.hotel.HotelResultResponse;
import com.dashotel.hotelmanagement.dto.response.paging.PagingResponse;
import com.dashotel.hotelmanagement.enums.HotelImageEnum;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface IHotelService {
    List<HotelImageTypeCountReponse> getHotelImageCategory(String hotelId);

    HotelImageResponse getHotelImages(String hotelId, HotelImageEnum hotelImageEnum);

    HotelDestailResponse getHotelDetail(String hotelId, LocalDate checkIn, LocalDate checkOut, Long numAdults, Long numRooms);

    CreationResponse addHotel(HotelCreationRequest request) throws IOException;

    CreationResponse addImageForHotel(HotelImageRequest request) throws IOException;

    List<HotelResultResponse> getHotelBySearch(LocalDate checkIn, LocalDate checkOut, Long numAdults, Long numRooms);

    PagingResponse<HotelResultResponse> getHotelBySearch(LocalDate checkIn, LocalDate checkOut, Long numAdults, Long numRooms,
                                                         int page, int size, String location);
}
