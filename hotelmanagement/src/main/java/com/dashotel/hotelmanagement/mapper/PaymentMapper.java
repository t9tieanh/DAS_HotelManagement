package com.dashotel.hotelmanagement.mapper;

import com.dashotel.hotelmanagement.dto.request.payment.PaymentRequest;
import com.dashotel.hotelmanagement.dto.response.payment.PaymentResponse;
import com.dashotel.hotelmanagement.entity.booking.PaymentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

public interface PaymentMapper {
    PaymentEntity toEntity(PaymentRequest request);
    PaymentResponse toResponse(PaymentEntity entity);
}
