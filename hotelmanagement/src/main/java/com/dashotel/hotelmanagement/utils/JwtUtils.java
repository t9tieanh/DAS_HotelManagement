package com.dashotel.hotelmanagement.utils;

import com.dashotel.hotelmanagement.entity.account.AccountEntity;
import com.dashotel.hotelmanagement.enums.TokenEnum;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.experimental.NonFinal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtils {

    private static final Logger log = LoggerFactory.getLogger(JwtUtils.class);
    @NonFinal
    @Value("${spring.jwt.signerKey}")
    protected String SIGNER_KEY;


    @Value("${spring.jwt.access-token-expiration}")
    private Long accessTokenExpiration;

    @Value("${spring.jwt.refresh-token-expiration}")
    private Long refreshTokenExpiration;

    @Value("${spring.jwt.short-lived-token}")
    private Long shortLiveTokenExpiration;



    public String generateToken (AccountEntity account, TokenEnum tokenType) throws JOSEException {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        Long date = (tokenType.equals(TokenEnum.ACCESS_TOKEN)) ? accessTokenExpiration : refreshTokenExpiration;
        if (tokenType.equals(TokenEnum.ACCESS_TOKEN)) {
            date = accessTokenExpiration;
        } else if (tokenType.equals(TokenEnum.RESFESH_TOKEN))
            date = refreshTokenExpiration;
        else if (tokenType.equals(TokenEnum.SHORT_LIVED_TOKEN))
            date = shortLiveTokenExpiration; // dành cho những token cos thời gian sống gắng

        //payload
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .jwtID(UUID.randomUUID().toString())
                .subject(account.getUsername()) // sub
                .issuer("freeclassroom.com") // iss
                .issueTime(new Date()) // iat
                .expirationTime(new Date(System.currentTimeMillis() + date))
                .claim("scope", account.getRole()) // Custom claim
                .claim("type",tokenType.name())
                .build();

        Payload payload = new Payload(claimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));

        return jwsObject.serialize();
    }


    public Boolean validToken (String token, TokenEnum tokenType) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);
        Date expirationDate = signedJWT.getJWTClaimsSet().getExpirationTime();
        TokenEnum tokenTypeEnum = TokenEnum.valueOf(signedJWT.getJWTClaimsSet().getClaim("type").toString());
//        String jwtId = signedJWT.getJWTClaimsSet().getJWTID();

//        String tokenvalue = tokenTypeEnum.getValue();
//        String tmp = tokenType.getValue();
        boolean flag = tokenTypeEnum.getValue().equals(tokenType.getValue());

        if (!(expirationDate.after(new Date()))
                || !signedJWT.verify(verifier) || !flag)
            return false;

        return true;
    }

    public String getJwtId (String token) throws ParseException {
        SignedJWT signedJWT = SignedJWT.parse(token);
        return signedJWT.getJWTClaimsSet().getJWTID();
    }

    public String getTokenFromSecurityContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null ) {
            Jwt token = (Jwt) authentication.getCredentials();
            return token.getTokenValue();
        }
        return null;
    }

}
