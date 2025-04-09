package com.dashotel.hotelmanagement.service.promotion;

import com.dashotel.hotelmanagement.dto.common.DiscountDTO;
import com.dashotel.hotelmanagement.dto.request.reservation.initial.ApplyDiscountRequest;
import com.dashotel.hotelmanagement.dto.response.CreationResponse;
import com.dashotel.hotelmanagement.entity.booking.ReservationEntity;
import com.dashotel.hotelmanagement.entity.promotion.DiscountEntity;
import com.dashotel.hotelmanagement.entity.user.CustomerEntity;
import com.dashotel.hotelmanagement.exception.CustomException;
import com.dashotel.hotelmanagement.exception.ErrorCode;
import com.dashotel.hotelmanagement.mapper.DiscountMapper;
import com.dashotel.hotelmanagement.repository.CustomerRepository;
import com.dashotel.hotelmanagement.repository.promotion.DiscountRepository;
import com.dashotel.hotelmanagement.repository.reservation.ReservationRepository;
import com.dashotel.hotelmanagement.utils.JwtUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class DiscountService {
    DiscountRepository discountRepository;
    CustomerRepository customerRepository;
    ReservationRepository reservationRepository;

    DiscountMapper discountMapper;
    JwtUtils jwtUtils;

    public CreationResponse addDiscount (DiscountDTO request) {

        if (discountRepository.existsByCode(request.getCode()))
            throw new CustomException(ErrorCode.USER_EXISTED); // mã code đã tồn tại

        DiscountEntity discountEntity = discountMapper.toEntity(request);
        discountEntity.setIsActive(true);

        discountEntity = discountRepository.save(discountEntity);

        return CreationResponse.builder()
                .isSuccess(true)
                .id(discountEntity.getId())
                .build();
    }

    // getAvailableDiscount
    public List<DiscountDTO> getAvailableDiscount () throws ParseException {
        String username = jwtUtils.getUsername();
        CustomerEntity customerEntity = customerRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException(ErrorCode.UNAUTHORIZED));

        return discountRepository.findAvailabeDiscountByLoyaltPoint(customerEntity.getLoyaltyPoints()).stream()
                .map(discountMapper::toDTO)
                .collect(Collectors.toList());
    }

    public DiscountDTO getDiscountByCode (String code) {

        return discountMapper.toDTO(
                discountRepository.findByCode(code).orElseThrow(
                        () -> new CustomException(ErrorCode.USER_NOT_FOUND)
                )
        );
    }

    public Boolean applyDiscount (ApplyDiscountRequest request) throws ParseException {
        String username = jwtUtils.getUsername();
        CustomerEntity customerEntity = customerRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException(ErrorCode.UNAUTHORIZED));


        ReservationEntity reservationEntity = reservationRepository.findById(request.getReservationId())
                        .orElseThrow(() -> new CustomException(ErrorCode.BOOKING_NOT_AVAILABLE));


        request.getDiscountCodes().forEach(discountCode -> {

            DiscountEntity discountEntity = discountRepository.findByCode(discountCode).orElseThrow(
                    () -> new CustomException(ErrorCode.DISCOUNT_NOT_AVAILABLE)
            );

            //// check available của discount
            if (discountEntity.getBeginDate().isAfter(LocalDate.now()) || discountEntity.getEndDate().isBefore(LocalDate.now()))
                throw new CustomException(ErrorCode.BOOKING_NOT_AVAILABLE);

            // chỉ check điều kiện với những discount public
            if (discountEntity.getIsPublic()) {
                if (discountEntity.getMinloyaltyPoints() > customerEntity.getLoyaltyPoints())
                    throw new CustomException(ErrorCode.INSUFFICIENT_MEMBER_POINTS);

                // các điều kiện khác
            }

            reservationEntity.getDiscounts().add(discountEntity);
        });

        reservationRepository.save(reservationEntity);
        return true;
    }


}
