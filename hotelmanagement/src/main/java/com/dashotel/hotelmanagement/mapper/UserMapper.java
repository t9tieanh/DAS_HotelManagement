package com.dashotel.hotelmanagement.mapper;

import com.dashotel.hotelmanagement.dto.request.CreationUserRequest;
import com.dashotel.hotelmanagement.dto.response.CreationUserResponse;
import com.dashotel.hotelmanagement.entity.user.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    void toUser (CreationUserRequest request, @MappingTarget UserEntity userEntity);

    CreationUserResponse toResponse (UserEntity user);
}
