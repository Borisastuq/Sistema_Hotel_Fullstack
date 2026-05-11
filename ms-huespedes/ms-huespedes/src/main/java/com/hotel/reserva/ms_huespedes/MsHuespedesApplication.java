package com.hotel.reserva.ms_huespedes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsHuespedesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsHuespedesApplication.class, args);
	}

}
