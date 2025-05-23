package com.dashotel.hotelmanagement.controller.AuthControllerTest;

import com.dashotel.hotelmanagement.dto.request.common.AuthenticationRequest;
import com.dashotel.hotelmanagement.dto.request.common.CreationUserRequest;
import com.dashotel.hotelmanagement.entity.account.AccountEntity;
import com.dashotel.hotelmanagement.enums.AccountStatusEnum;
import com.dashotel.hotelmanagement.enums.RoleAccountEnum;
import com.dashotel.hotelmanagement.enums.TokenEnum;
import com.dashotel.hotelmanagement.repository.AccountRepository;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.Assert.*;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@Transactional
public class SignUpIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AccountRepository accountRepository;

    private CreationUserRequest customerRequest;

    private CreationUserRequest adminRequest;

    @Before
    public void initData(){
        customerRequest = CreationUserRequest.builder()
                .name("Phạm Tiến Anh")
                .phone("0856230326")
                .email("test123@gmail.com")
                .role(RoleAccountEnum.CUSTOMER)
                .username("testaccount")
                .password("1")
                .build();

        adminRequest = CreationUserRequest.builder()
                .name("Phạm Tiến Anh")
                .phone("0856230326")
                .email("test123@gmail.com")
                .role(RoleAccountEnum.ADMIN)
                .username("testaccount")
                .password("1")
                .build();
    }

    @Test
    public void signUpWithCustomer() throws Exception {
        // WHEN, THEN
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .multipart("/auth/sign-up")
                        .param("name", customerRequest.getName())
                        .param("phone", customerRequest.getPhone())
                        .param("email", customerRequest.getEmail())
                        .param("role", customerRequest.getRole().toString())
                        .param("username", customerRequest.getUsername())
                        .param("password", customerRequest.getPassword())
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(200))
                .andReturn();

        String responseString = result.getResponse().getContentAsString();
        JsonNode jsonNode = objectMapper.readTree(responseString);
        String userName = jsonNode.path("result").path("username").asText();

        Optional<AccountEntity> accountEntity = accountRepository.findByUsername(userName);
        assertTrue(accountEntity.isPresent());
        assertEquals(AccountStatusEnum.UNVERIFIED, accountEntity.get().getStatus());
    }



    @Test
    public void signUpWithAdminSucess() throws Exception {
        // WHEN, THEN
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .multipart("/auth/sign-up")
                        .param("name", adminRequest.getName())
                        .param("phone", adminRequest.getPhone())
                        .param("email", adminRequest.getEmail())
                        .param("role", adminRequest.getRole().toString())
                        .param("username", adminRequest.getUsername())
                        .param("password", adminRequest.getPassword())
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(200))
                .andReturn();

        String responseString = result.getResponse().getContentAsString();
        JsonNode jsonNode = objectMapper.readTree(responseString);
        String userName = jsonNode.path("result").path("username").asText();

        Optional<AccountEntity> accountEntity = accountRepository.findByUsername(userName);
        assertTrue(accountEntity.isPresent());
        assertEquals(AccountStatusEnum.ACTIVE, accountEntity.get().getStatus());
    }


    @Test
    public void signUpWithAdminFailure() throws Exception {
        // WHEN, THEN
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .multipart("/auth/sign-up")
                        .param("name", adminRequest.getName())
                        .param("phone", adminRequest.getPhone())
                        .param("email", adminRequest.getEmail())
                        .param("role", adminRequest.getRole().toString())
                        .param("username", "admin")
                        .param("password", adminRequest.getPassword())
                )
                .andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(1001))
                .andReturn();

        String responseString = result.getResponse().getContentAsString();
        JsonNode jsonNode = objectMapper.readTree(responseString);
        String message = jsonNode.path("message").asText();

        // kiểm tra hợp lệ của token
        log.info("Message của việc sai mật khẩu: {}", message);
    }

}
