package com.dashotel.hotelmanagement.service.user;

import com.dashotel.hotelmanagement.dto.request.common.CreationUserRequest;
import com.dashotel.hotelmanagement.dto.response.common.CreationUserResponse;
import com.dashotel.hotelmanagement.entity.account.AccountEntity;
import com.dashotel.hotelmanagement.entity.user.AdminEntity;
import com.dashotel.hotelmanagement.entity.user.CustomerEntity;
import com.dashotel.hotelmanagement.entity.user.OwnerEntity;
import com.dashotel.hotelmanagement.entity.user.UserEntity;
import com.dashotel.hotelmanagement.enums.AccountStatusEnum;
import com.dashotel.hotelmanagement.enums.RoleAccountEnum;
import com.dashotel.hotelmanagement.exception.CustomException;
import com.dashotel.hotelmanagement.exception.ErrorCode;
import com.dashotel.hotelmanagement.mapper.AccountMapper;
import com.dashotel.hotelmanagement.mapper.UserMapper;
import com.dashotel.hotelmanagement.repository.AccountRepository;
import com.dashotel.hotelmanagement.service.other.FileStorageService;
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

    // chỉ dùng cho
    @Transactional
    public boolean activeAccount (CreationUserRequest request) throws IOException {
        AccountEntity accountEntity = accountRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        // không cho trùng username
        if (accountRepository.existsByUsername(request.getUsername()))
            throw new CustomException(ErrorCode.USER_EXISTED);

        // tiến hành lưu ảnh
        request.setImgUrl(fileStorageService.storeImage(request.getImg()));

        request.setRole(RoleAccountEnum.CUSTOMER); // chỉ có customer mới được tạo tài khoản google
        updateAccount(request, accountEntity);

        //update password
        accountEntity.setPassword(passwordEncoder.encode(request.getPassword()));
        accountEntity.setStatus(AccountStatusEnum.ACTIVE); // set status -> active
        accountMapper.updateAccountEntity(request, accountEntity);

        accountRepository.save(accountEntity);
        return true;
    }


    @Transactional
    public CreationUserResponse addUser (CreationUserRequest request) throws IOException {
        if (accountRepository.existsByUsername(request.getUsername())
                || accountRepository.existsByEmail(request.getEmail())) {
            throw new CustomException(ErrorCode.USER_EXISTED); // user đã tồn tại
        }
        request.setRole(RoleAccountEnum.CUSTOMER);
        AccountEntity accountEntity = accountMapper.toAccountEntity(request);
        // tiến hành lưu ảnh
        if(request.getImgUrl() != null) {
            request.setImgUrl(fileStorageService.storeImage(request.getImg()));
        }

        updateAccount(request, accountEntity);

        //update password
        accountEntity.setPassword(passwordEncoder.encode(accountEntity.getPassword()));
        accountEntity.setStatus(AccountStatusEnum.ACTIVE);

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
                ((CustomerEntity) userEntity).setLoyaltyPoints(0L); // xét điểm ban đầu = 0
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

    // viết cho android
    public Boolean emailExists (String email) {
        return accountRepository.existsByEmail(email);
    }
}
