package com.dashotel.hotelmanagement.service.user;

import com.dashotel.hotelmanagement.dto.request.CustomerRequestDTO;
import com.dashotel.hotelmanagement.dto.response.CustomerResponseDTO;
import com.dashotel.hotelmanagement.entity.user.CustomerEntity;
import com.dashotel.hotelmanagement.mapper.CustomerMapper;
import com.dashotel.hotelmanagement.repository.CustomerRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerService {
    CustomerRepository customerRepository;

    CustomerMapper customerMapper;

    @Transactional
    public CustomerResponseDTO editProfile(String customerId, CustomerRequestDTO request) {
        CustomerEntity entity = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        customerMapper.updateProfile(request, entity);
        entity = customerRepository.save(entity);

        return customerMapper.toDTO(entity);
    }

    @Transactional
    public CustomerResponseDTO getCustomerById(String customerId) {
        CustomerEntity entity = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        return customerMapper.toDTO(entity);
    }
}
