package com.dashotel.hotelmanagement.service.auth;

import com.dashotel.hotelmanagement.dto.request.AuthenticationRequest;
import com.dashotel.hotelmanagement.dto.request.CreationUserRequest;
import com.dashotel.hotelmanagement.dto.request.LogoutRequest;
import com.dashotel.hotelmanagement.dto.response.AuthenticationResponse;
import com.dashotel.hotelmanagement.dto.response.CreationUserResponse;
import com.dashotel.hotelmanagement.entity.account.AccountEntity;
import com.dashotel.hotelmanagement.entity.auth.InvalidTokenEntity;
import com.dashotel.hotelmanagement.entity.peple.AdminEntity;
import com.dashotel.hotelmanagement.entity.peple.CustomerEntity;
import com.dashotel.hotelmanagement.entity.peple.OwnerEntity;
import com.dashotel.hotelmanagement.entity.peple.UserEntity;
import com.dashotel.hotelmanagement.enums.RoleAccountEnum;
import com.dashotel.hotelmanagement.exception.CustomException;
import com.dashotel.hotelmanagement.exception.ErrorCode;
import com.dashotel.hotelmanagement.mapper.AccountMapper;
import com.dashotel.hotelmanagement.mapper.UserMapper;
import com.dashotel.hotelmanagement.repository.*;
import com.dashotel.hotelmanagement.service.other.FileStorageService;
import com.dashotel.hotelmanagement.utils.JwtUtils;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {

    AccountRepository accountRepository;
    InvalidTokenRepository invalidTokenRepository;

    PasswordEncoder passwordEncoder;

    JwtUtils jwtUtils;

    public AuthenticationResponse authentication (AuthenticationRequest request) throws JOSEException {
        AccountEntity account = accountRepository.findByUsername(request.getUsername()).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND)
        );

//        boolean result = passwordEncoder.matches(request.getPassword(), account.getPassword());

        // test
        boolean result = false;
        if (account != null) {
            result = true;
        }

        if (result) {
            return AuthenticationResponse.builder()
                    .token(jwtUtils.generateToken(account))
                    .username(account.getUsername())
                    .role(account.getRole())
                    .valid(true)
                    .build();
        }

        throw new CustomException (ErrorCode.UN_AUTHENTICATED);
    }

    public void logOut (LogoutRequest request) throws ParseException {
        SignedJWT signedJWT = SignedJWT.parse(request.getToken());
        String jwtId = signedJWT.getJWTClaimsSet().getJWTID().toString();
        Date expirationDate = signedJWT.getJWTClaimsSet().getExpirationTime();

        invalidTokenRepository.save(new InvalidTokenEntity(jwtId,expirationDate));
    }

    public Boolean introspect (String token) throws JOSEException, ParseException {
        String jwtId = jwtUtils.getJwtId(token);
        return !invalidTokenRepository.existsById(jwtId) && jwtUtils.validToken(token);
    }

}
