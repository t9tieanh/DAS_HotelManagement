package com.dashotel.hotelmanagement.controller.AuthControllerTest;

import com.dashotel.hotelmanagement.dto.request.common.AuthenticationRequest;
import com.dashotel.hotelmanagement.enums.TokenEnum;
import com.dashotel.hotelmanagement.utils.JwtUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.junit.Assert.assertTrue;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class LoginIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtUtils jwtUtils;

    private AuthenticationRequest requestSuccess;

    private AuthenticationRequest requestFailure;

    @Before
    public void initData(){
        requestSuccess = AuthenticationRequest.builder()
                .username("admin")
                .password("admin")
                .build();

        requestFailure = AuthenticationRequest.builder()
                .username("admin")
                .password("mksai")
                .build();
    }

    @Test
    public void login_validRequest_success() throws Exception {
        String content = objectMapper.writeValueAsString(requestSuccess);

        // WHEN, THEN
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                                .post("/auth/login")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(content))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("code")
                                .value(200)
                        ).andReturn();

        String responseString = result.getResponse().getContentAsString();
        JsonNode jsonNode = objectMapper.readTree(responseString);
        String accessToken = jsonNode.path("result").path("accessToken").asText();
        String reshfeshToken = jsonNode.path("result").path("refreshToken").asText();

        // kiểm tra hợp lệ của token
        assertTrue(jwtUtils.validToken(accessToken, TokenEnum.ACCESS_TOKEN)
            && jwtUtils.validToken(reshfeshToken, TokenEnum.RESFESH_TOKEN));
    }


    // truo
    @Test
    public void login_validRequest_fail() throws Exception {
        String content = objectMapper.writeValueAsString(requestFailure);

        // WHEN, THEN
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andExpect(MockMvcResultMatchers.jsonPath("code")
                        .value(1009)
                ).andReturn();

        String responseString = result.getResponse().getContentAsString();
        JsonNode jsonNode = objectMapper.readTree(responseString);
        String message = jsonNode.path("message").asText();

        // kiểm tra hợp lệ của token
        log.info("Message của việc sai mật khẩu: ",message);
    }
}
