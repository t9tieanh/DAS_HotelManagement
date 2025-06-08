package com.dashotel.hotelmanagement.controller.ReservationControllerTest;

import com.dashotel.hotelmanagement.dto.request.common.AuthenticationRequest;
import com.dashotel.hotelmanagement.dto.request.reservation.initial.InitialReservationRequest;
import com.dashotel.hotelmanagement.service.impl.auth.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.time.LocalDate;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@Transactional
public class ReservationIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    private String token;

    private AuthenticationService authenticationService;

    @Before
    public void initData() throws ParseException, JOSEException {
        token = authenticationService.authentication(
                AuthenticationRequest.builder()
                        .username("admin")
                        .password("admin")
                        .build()
        ).getAccessToken();
    }

    @Test
    public void testCreateReservation_ValidRequest_ShouldReturnSuccess() throws ParseException {
        // Arrange
//        InitialReservationRequest request = new InitialReservationRequest();
//        request.setCheckIn(LocalDate.now());
//        request.setCheckOut(LocalDate.now().plusDays(1));
//        request.setReservationDetails();
//        request.setNumRooms(1L);
//
//        
//
//        // Act
//        ApiResponse<InitialReservationResponse> response = reservationController.createReservation(request);
//
//        // Assert
//        assertEquals(200, response.getCode());
//        assertNotNull(response.getResult());
//        assertEquals("Hotel ABC", response.getResult().getHotelName());
//        assertEquals(500.0, response.getResult().getTotalPrice());
    }
}
