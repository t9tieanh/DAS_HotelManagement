package com.dashotel.hotelmanagement;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableFeignClients
@EnableJpaAuditing
public class DasHotelApplication {

	static {
		Dotenv dotenv = Dotenv.configure().filename("params.env").load(); // Load file .env
		dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
	}
	public static void main(String[] args) {
		SpringApplication.run(DasHotelApplication.class, args);
	}

}

//@SpringBootApplication
//@EnableFeignClients
//@EnableJpaAuditing
//public class DasHotelApplication {
//
//	static {
//		Dotenv dotenv = Dotenv.configure()
//				.directory("/app")
//				.filename("params.env")
//				.load(); // Load file .env
//		dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
//	}
//
//	public static void main(String[] args) {
//		SpringApplication.run(DasHotelApplication.class, args);
//	}
//}