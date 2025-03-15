package com.dashotel.hotelmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DasHotelApplication {

	public static void main(String[] args) {
		SpringApplication.run(DasHotelApplication.class, args);
	}

}
