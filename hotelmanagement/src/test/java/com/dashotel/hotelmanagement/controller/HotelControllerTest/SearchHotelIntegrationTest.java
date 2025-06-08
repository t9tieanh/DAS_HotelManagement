package com.dashotel.hotelmanagement.controller.HotelControllerTest;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class SearchHotelIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetListHotel_ValidInput_ShouldReturnOk() throws Exception {
        LocalDate checkIn = LocalDate.of(2025, 5, 25);
        LocalDate checkOut = LocalDate.of(2025, 5, 27);
        Long numAdults = 2L;
        Long numRooms = 1L;
        String location = "Da Nang";

        mockMvc.perform(get("/hotel/search-hotel")
                        .param("checkIn", checkIn.toString())
                        .param("checkOut", checkOut.toString())
                        .param("numAdults", numAdults.toString())
                        .param("numRooms", numRooms.toString())
                        .param("location", location)
                        .param("page", "0")
                        .param("size", "3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.result").exists())
                .andExpect(jsonPath("$.result.content").isArray())
                .andDo(print());
    }


    // checkIn > checkOut
    @Test
    public void testGetListHotel_InvalidDateRange_ShouldReturnRoomNotFoundError() throws Exception {
        mockMvc.perform(get("/hotel/search-hotel")
                        .param("checkIn", "2025-05-28")
                        .param("checkOut", "2025-05-25")
                        .param("numAdults", "2")
                        .param("numRooms", "1"))
                .andExpect(status().is4xxClientError()) // có thể là 400 hoặc 404
                .andExpect(jsonPath("$.code").value(1012)) // giả sử ErrorCode.ROOM_NOT_FOUND = 1004
                .andDo(print());
    }

    // numAdult < 1
    @Test
    public void testGetListHotel_NumAdultsLessThan1_ShouldReturnError() throws Exception {
        mockMvc.perform(get("/hotel/search-hotel")
                        .param("checkIn", "2025-05-25")
                        .param("checkOut", "2025-05-27")
                        .param("numAdults", "0")
                        .param("numRooms", "1"))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.code").value(1012)) // ROOM_NOT_FOUND
                .andDo(print());
    }


    @Test
    public void testGetListHotel_LocationAll_ShouldReturnOk() throws Exception {
        mockMvc.perform(get("/hotel/search-hotel")
                        .param("checkIn", "2025-05-25")
                        .param("checkOut", "2025-05-27")
                        .param("numAdults", "2")
                        .param("numRooms", "1")
                        .param("location", "all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.result").exists())
                .andExpect(jsonPath("$.result.content").isArray())
                .andDo(print());
    }


    @Test
    public void testGetListHotel_LocationTatCa_ShouldReturnOk() throws Exception {
        mockMvc.perform(get("/hotel/search-hotel")
                        .param("checkIn", "2025-05-25")
                        .param("checkOut", "2025-05-27")
                        .param("numAdults", "2")
                        .param("numRooms", "1")
                        .param("location", "Tất cả"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andDo(print());
    }
}
