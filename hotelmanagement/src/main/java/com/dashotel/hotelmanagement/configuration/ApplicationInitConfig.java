package com.dashotel.hotelmanagement.configuration;

import com.dashotel.hotelmanagement.dto.request.common.CreationUserRequest;
import com.dashotel.hotelmanagement.enums.RoleAccountEnum;
import com.dashotel.hotelmanagement.repository.AccountRepository;
import com.dashotel.hotelmanagement.service.impl.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class ApplicationInitConfig {

    @Bean
    @ConditionalOnProperty(prefix = "spring",
            value = "datasource.driver-class-name",
            havingValue = "com.mysql.cj.jdbc.Driver"
    )
    ApplicationRunner applicationRunner(UserService userService, AccountRepository accountRepository){
        log.info("Init application.....");
        return args -> {
            if (accountRepository.findByUsername("admin").isEmpty()){

                userService.addUser(
                        CreationUserRequest.builder()
                                .username("admin")
                                .password("admin")
                                .role(RoleAccountEnum.ADMIN)
                                .name("Phạm Tiến Anh")
                                .build()
                );
                log.warn("admin user has been created with default password: admin, please change it");
            }
        };
    }

}
