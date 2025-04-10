package com.dashotel.hotelmanagement.mapper;

import com.dashotel.hotelmanagement.dto.common.DiscountDTO;
import com.dashotel.hotelmanagement.entity.promotion.DiscountEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DiscountMapper {
    DiscountEntity toEntity (DiscountDTO request);
    DiscountDTO toDTO (DiscountEntity request);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "discountPrecentage", source = "discountPrecentage")
    @Mapping(target = "code", source = "code")
    DiscountDTO toDiscountDTOForReservation(DiscountEntity request);
}
