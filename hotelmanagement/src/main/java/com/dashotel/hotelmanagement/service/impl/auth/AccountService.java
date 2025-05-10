package com.dashotel.hotelmanagement.service.impl.auth;

import com.dashotel.hotelmanagement.entity.account.AccountEntity;
import com.dashotel.hotelmanagement.repository.AccountRepository;
import com.dashotel.hotelmanagement.service.user.IAccountService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountService implements IAccountService {
    AccountRepository accountRepository;

    @Transactional
    public String getAccountIdByUsername(String username) {
        return accountRepository.findByUsername(username)
                .map(AccountEntity::getId)
                .orElseThrow(() -> new RuntimeException("Not found Account with Username: " + username));
    }
}
