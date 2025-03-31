package com.dashotel.hotelmanagement.service.auth;

import com.dashotel.hotelmanagement.entity.account.AccountEntity;
import com.dashotel.hotelmanagement.repository.AccountRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountService {
    AccountRepository accountRepository;

    @Transactional
    public String getAccountIdByUsername(String username) {
        return accountRepository.findByUsername(username)
                .map(AccountEntity::getId)
                .orElseThrow(() -> new RuntimeException("Not found Account with Username: " + username));
    }

}
