package com.dashotel.hotelmanagement.service.auth;

import com.dashotel.hotelmanagement.dto.request.common.AuthenticationRequest;
import com.dashotel.hotelmanagement.dto.response.common.AuthenticationResponse;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;

public interface IAuthenticationService {
    AuthenticationResponse outBoundAuthentication(String code) throws JOSEException;

    AuthenticationResponse authentication(AuthenticationRequest request) throws JOSEException, ParseException;

    void logOut() throws ParseException;

    Boolean introspect(String token) throws JOSEException, ParseException;

    String getCurrentUserId();
}
