package com.dashotel.hotelmanagement.service.user;

import com.dashotel.hotelmanagement.dto.request.CreationUserRequest;
import com.dashotel.hotelmanagement.dto.response.CreationUserResponse;
import com.dashotel.hotelmanagement.entity.account.AccountEntity;
import com.dashotel.hotelmanagement.entity.peple.AdminEntity;
import com.dashotel.hotelmanagement.entity.peple.CustomerEntity;
import com.dashotel.hotelmanagement.entity.peple.OwnerEntity;
import com.dashotel.hotelmanagement.entity.peple.UserEntity;
import com.dashotel.hotelmanagement.enums.RoleAccountEnum;
import com.dashotel.hotelmanagement.exception.CustomException;
import com.dashotel.hotelmanagement.exception.ErrorCode;
import com.dashotel.hotelmanagement.mapper.AccountMapper;
import com.dashotel.hotelmanagement.mapper.UserMapper;
import com.dashotel.hotelmanagement.repository.AccountRepository;
import com.dashotel.hotelmanagement.repository.AdminRepository;
import com.dashotel.hotelmanagement.repository.CustomerRepository;
import com.dashotel.hotelmanagement.repository.OwnerRepository;
import com.dashotel.hotelmanagement.service.other.FileStorageService;
import com.dashotel.hotelmanagement.utils.JwtUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    AccountRepository accountRepository;

    UserMapper userMapper;
    AccountMapper accountMapper;
    PasswordEncoder passwordEncoder;

    FileStorageService fileStorageService;

    @Transactional
    public CreationUserResponse addUser (CreationUserRequest request) throws IOException {
        if (accountRepository.existsByUsername(request.getUsername())
                || accountRepository.existsByEmail(request.getEmail())) {
            throw new CustomException(ErrorCode.USER_EXISTED); // user đã tồn tại
        }

        AccountEntity accountEntity = accountMapper.toAccountEntity(request);

        // tiến hành lưu ảnh
        request.setImgUrl(fileStorageService.storeImage(request.getImg()));

        updateAccount(request, accountEntity);

        //update password
        accountEntity.setPassword(passwordEncoder.encode(accountEntity.getPassword()));

        return accountMapper.toResponse(accountRepository.save(accountEntity));
    }

    void updateAccount (CreationUserRequest request, AccountEntity accountEntity) {
        UserEntity userEntity;

        switch (request.getRole()) {
            case RoleAccountEnum.ADMIN:
                userEntity = new AdminEntity();
                accountEntity.setAdmin((AdminEntity) userEntity);
                ((AdminEntity) userEntity).setAccount(accountEntity);
                break;

            case RoleAccountEnum.CUSTOMER:
                userEntity = new CustomerEntity();
                accountEntity.setCustomer((CustomerEntity) userEntity);
                ((CustomerEntity) userEntity).setAccount(accountEntity);
                break;

            case RoleAccountEnum.OWNER:
                userEntity = new OwnerEntity();
                accountEntity.setOwner((OwnerEntity) userEntity);
                ((OwnerEntity) userEntity).setAccount(accountEntity);
                break;

            default: throw new CustomException(ErrorCode.INVALID_KEY);
        }

        // update user từ request
        userMapper.toUser(request,userEntity);
    }
}
