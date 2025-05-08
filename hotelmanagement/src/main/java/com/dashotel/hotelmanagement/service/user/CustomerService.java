package com.dashotel.hotelmanagement.service.user;

import com.dashotel.hotelmanagement.dto.request.common.CustomerRequestDTO;
import com.dashotel.hotelmanagement.dto.response.common.CreationResponse;
import com.dashotel.hotelmanagement.dto.response.common.CustomerResponseDTO;
import com.dashotel.hotelmanagement.entity.account.AccountEntity;
import com.dashotel.hotelmanagement.entity.user.CustomerEntity;
import com.dashotel.hotelmanagement.exception.CustomException;
import com.dashotel.hotelmanagement.exception.ErrorCode;
import com.dashotel.hotelmanagement.mapper.CustomerMapper;
import com.dashotel.hotelmanagement.repository.AccountRepository;
import com.dashotel.hotelmanagement.repository.CustomerRepository;
import com.dashotel.hotelmanagement.service.other.FileStorageService;
import com.dashotel.hotelmanagement.utils.JwtUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerService {

    CustomerRepository customerRepository;

    AccountRepository accountRepository;

    JwtUtils jwtUtils;

    FileStorageService fileStorageService;

    CustomerMapper customerMapper;

    @Transactional
    public CustomerResponseDTO editProfile(String customerId, CustomerRequestDTO request) {
        CustomerEntity entity = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        customerMapper.updateProfile(request, entity);
        entity = customerRepository.save(entity);

        return customerMapper.toDTO(entity);
    }

    @Transactional
    public CustomerResponseDTO getCustomerById(String customerId) {
        CustomerEntity entity = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        return customerMapper.toDTO(entity);
    }

    @Transactional
    public String getCustomerIdByAccountId(String accountId) {
        return Optional.ofNullable(customerRepository.findByAccountId(accountId))
                .map(CustomerEntity::getId)
                .orElseThrow(() -> new RuntimeException("Not found customer with accountId"));
    }

    @Transactional
    public CreationResponse updateImage (MultipartFile file) throws ParseException, IOException {
        String userName = jwtUtils.getUsername();

        AccountEntity accountEntity = accountRepository.findByUsername(userName)
                .orElseThrow(() -> new CustomException(ErrorCode.UNAUTHORIZED));

        // tiến hành lưu ảnh
        accountEntity.setImgUrl(fileStorageService.storeImage(file));

        // tiến hành lưu
        accountRepository.save(accountEntity);

        return CreationResponse.builder().id(accountEntity.getImgUrl()).build(); // trả về url của ảnh mới được lưu
    }
}
