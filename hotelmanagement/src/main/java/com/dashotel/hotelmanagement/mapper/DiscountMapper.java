package com.dashotel.hotelmanagement.mapper;

import com.dashotel.hotelmanagement.dto.common.DiscountDTO;
import com.dashotel.hotelmanagement.entity.promotion.DiscountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DiscountMapper {
    DiscountEntity toEntity (DiscountDTO request);
    DiscountDTO toDTO (DiscountEntity request);
}
