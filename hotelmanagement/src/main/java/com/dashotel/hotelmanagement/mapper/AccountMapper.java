package com.dashotel.hotelmanagement.mapper;

import com.dashotel.hotelmanagement.dto.request.common.CreationUserRequest;
import com.dashotel.hotelmanagement.dto.response.common.CreationUserResponse;
import com.dashotel.hotelmanagement.entity.account.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    void updateAccountEntity (CreationUserRequest request, @MappingTarget AccountEntity account);
    AccountEntity toAccountEntity (CreationUserRequest request);
    CreationUserResponse toResponse (AccountEntity account);
}
