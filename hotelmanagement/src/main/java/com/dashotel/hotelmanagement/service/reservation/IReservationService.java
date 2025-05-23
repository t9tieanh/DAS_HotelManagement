package com.dashotel.hotelmanagement.service.reservation;

import com.dashotel.hotelmanagement.dto.common.DiscountDTO;
import com.dashotel.hotelmanagement.dto.common.ResponseDTO;
import com.dashotel.hotelmanagement.dto.request.reservation.initial.ApplyDiscountRequest;
import com.dashotel.hotelmanagement.dto.request.reservation.initial.InitialReservationRequest;
import com.dashotel.hotelmanagement.dto.request.reservation.updateinfo.UpdateReservationInfoRequest;
import com.dashotel.hotelmanagement.dto.response.common.CreationResponse;
import com.dashotel.hotelmanagement.dto.response.reservation.ApplyDiscountResponse;
import com.dashotel.hotelmanagement.dto.response.reservation.InitialReservationResponse;
import com.dashotel.hotelmanagement.dto.response.reservation.ReservationStepResponse;
import com.dashotel.hotelmanagement.dto.response.reservation.common.ReservationResponse;
import com.dashotel.hotelmanagement.dto.response.reservation.history.ReservationHistoryResponse;
import com.dashotel.hotelmanagement.enums.BookingStatusEnum;

import java.text.ParseException;
import java.util.List;

public interface IReservationService {
    InitialReservationResponse createReservation(InitialReservationRequest request) throws ParseException;

    CreationResponse updateCustomerInfoReservation(UpdateReservationInfoRequest request);

    ResponseDTO cancelReservation(String id);

    ReservationStepResponse getCurrentStep(String reservationId);

    Double getTotalPrice(String reservationId);

    ApplyDiscountResponse applyDiscount(ApplyDiscountRequest request) throws ParseException;

    List<DiscountDTO> getDiscountByReservation(String reservationId);

    boolean isOwnerOfReservation(String reservationId, String username);

    ReservationResponse updateReservationStatus(String reservationId, BookingStatusEnum bookingStatus);

    List<ReservationHistoryResponse> getReservationHistory(String customerId);

    Long getReservationCompletedCount(String customerId);
}
