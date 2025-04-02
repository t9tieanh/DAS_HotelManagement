package com.dashotel.hotelmanagement.service.promotion;

import com.dashotel.hotelmanagement.dto.common.DiscountDTO;
import com.dashotel.hotelmanagement.dto.response.CreationResponse;
import com.dashotel.hotelmanagement.entity.promotion.DiscountEntity;
import com.dashotel.hotelmanagement.exception.CustomException;
import com.dashotel.hotelmanagement.exception.ErrorCode;
import com.dashotel.hotelmanagement.mapper.DiscountMapper;
import com.dashotel.hotelmanagement.repository.promotion.DiscountRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class DiscountService {
    DiscountRepository discountRepository;

    DiscountMapper discountMapper;

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

    public List<DiscountDTO> getAvailableDiscount () {

        return discountRepository.findAvailabeDiscount().stream()
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


}
