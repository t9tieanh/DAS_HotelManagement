package com.dashotel.hotelmanagement.service.user;

import com.dashotel.hotelmanagement.dto.request.CustomerRequestDTO;
import com.dashotel.hotelmanagement.dto.response.CustomerResponseDTO;
import com.dashotel.hotelmanagement.entity.user.CustomerEntity;
import com.dashotel.hotelmanagement.exception.CustomException;
import com.dashotel.hotelmanagement.exception.ErrorCode;
import com.dashotel.hotelmanagement.mapper.CustomerMapper;
import com.dashotel.hotelmanagement.repository.CustomerRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerService {
    CustomerRepository customerRepository;

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
}
