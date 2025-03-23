package com.dashotel.hotelmanagement.mapper;

import com.dashotel.hotelmanagement.dto.request.CreationUserRequest;
import com.dashotel.hotelmanagement.dto.response.CreationUserResponse;
import com.dashotel.hotelmanagement.entity.account.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    void updateAccountEntity (CreationUserRequest request, @MappingTarget AccountEntity account);
    AccountEntity toAccountEntity (CreationUserRequest request);
    CreationUserResponse toResponse (AccountEntity account);
}
