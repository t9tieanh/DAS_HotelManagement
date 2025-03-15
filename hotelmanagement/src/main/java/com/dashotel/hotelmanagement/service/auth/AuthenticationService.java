package com.dashotel.hotelmanagement.service.auth;

import ch.qos.logback.core.spi.ErrorCodes;
import com.dashotel.hotelmanagement.dto.request.AuthenticationRequest;
import com.dashotel.hotelmanagement.dto.request.CreationUserRequest;
import com.dashotel.hotelmanagement.dto.request.ExchanceTokenRequest;
import com.dashotel.hotelmanagement.dto.request.LogoutRequest;
import com.dashotel.hotelmanagement.dto.response.AuthenticationResponse;
import com.dashotel.hotelmanagement.dto.response.CreationUserResponse;
import com.dashotel.hotelmanagement.dto.response.UserInfoResponse;
import com.dashotel.hotelmanagement.entity.account.AccountEntity;
import com.dashotel.hotelmanagement.entity.auth.InvalidTokenEntity;
import com.dashotel.hotelmanagement.entity.peple.AdminEntity;
import com.dashotel.hotelmanagement.entity.peple.CustomerEntity;
import com.dashotel.hotelmanagement.entity.peple.OwnerEntity;
import com.dashotel.hotelmanagement.entity.peple.UserEntity;
import com.dashotel.hotelmanagement.enums.AccountStatusEnum;
import com.dashotel.hotelmanagement.enums.RoleAccountEnum;
import com.dashotel.hotelmanagement.enums.TokenEnum;
import com.dashotel.hotelmanagement.exception.CustomException;
import com.dashotel.hotelmanagement.exception.ErrorCode;
import com.dashotel.hotelmanagement.mapper.AccountMapper;
import com.dashotel.hotelmanagement.mapper.UserMapper;
import com.dashotel.hotelmanagement.repository.*;
import com.dashotel.hotelmanagement.repository.httpclient.OutboundAuthenticateClient;
import com.dashotel.hotelmanagement.repository.httpclient.OutboundUserInfoClient;
import com.dashotel.hotelmanagement.service.other.FileStorageService;
import com.dashotel.hotelmanagement.utils.JwtUtils;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
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
    OutboundAuthenticateClient outboundAuthenticateClient;
    OutboundUserInfoClient outboundUserInfoClient;

    PasswordEncoder passwordEncoder;

    JwtUtils jwtUtils;

    @NonFinal
    @Value("${outbound.identity.client-id}")
    protected String CLIENT_ID;

    @NonFinal
    @Value("${outbound.identity.client-secret}")
    protected String CLIENT_SECRET ;

    @NonFinal
    @Value("${outbound.identity.redirect-uri}")
    protected String REDIRECT_URL ;

    @NonFinal
    protected String GRANT_TYPE = "authorization_code";

    public AuthenticationResponse outBoundAuthentication (String code) throws JOSEException {
        var reponse = outboundAuthenticateClient.exchanceToken(
                ExchanceTokenRequest.builder()
                        .code(code)
                        .clientId(CLIENT_ID)
                        .clientSecret(CLIENT_SECRET)
                        .redirectUri(REDIRECT_URL)
                        .grantType(GRANT_TYPE)
                        .build()
        );

        UserInfoResponse userInfo = outboundUserInfoClient.getUserInfo("json",reponse.getAccessToken());

        var account = accountRepository.findByEmail(userInfo.getEmail()).orElseGet(
                () -> {
                    return accountRepository.save(AccountEntity.builder()
                            .email(userInfo.getEmail())
                            .status(AccountStatusEnum.UNVERIFIED)
                            .build());
                }
        );

        return (account.getStatus() != AccountStatusEnum.ACTIVE) ?
                AuthenticationResponse.builder() // người dùng mới -> chỉ trả về những thông tin để người ta đăng nhập
                        .name(userInfo.getName())
                        .accessToken(jwtUtils.generateToken(account,TokenEnum.SHORT_LIVED_TOKEN)) // trả về một token có thời gian ngắn
                        .email(userInfo.getEmail())
                        .imageUrl(userInfo.getPicture())
                        .isActive(account.getStatus() == AccountStatusEnum.ACTIVE)
                        .build()

                :

                AuthenticationResponse.builder()
                        .accessToken(jwtUtils.generateToken(account,TokenEnum.ACCESS_TOKEN))
                        .refreshToken(jwtUtils.generateToken(account,TokenEnum.RESFESH_TOKEN))
                        .email(account.getEmail())
                        .username(account.getUsername())
                        .role(account.getRole())
                        .isActive(account.getStatus() == AccountStatusEnum.ACTIVE)
                        .valid(true)
                        .build();
    }


    public AuthenticationResponse authentication (AuthenticationRequest request) throws JOSEException {
        AccountEntity account = accountRepository.findByUsername(request.getUsername()).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND)
        );

        // nếu tài khoản chưa active
        if (account.getStatus() != AccountStatusEnum.ACTIVE) {
            throw new CustomException(ErrorCode.ACCOUNT_UN_ACTIVE);
        }

//        boolean result = passwordEncoder.matches(request.getPassword(), account.getPassword());

        // test
        boolean result = false;
        if (account != null) {
            result = true;
        }

        if (result) {
            return AuthenticationResponse.builder()
                    .accessToken(jwtUtils.generateToken(account,TokenEnum.ACCESS_TOKEN))
                    .refreshToken(jwtUtils.generateToken(account,TokenEnum.RESFESH_TOKEN))
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
        return !invalidTokenRepository.existsById(jwtId) &&
                (jwtUtils.validToken(token,TokenEnum.ACCESS_TOKEN) || jwtUtils.validToken(token,TokenEnum.SHORT_LIVED_TOKEN));
    }

}