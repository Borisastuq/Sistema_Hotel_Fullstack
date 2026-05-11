package com.hotel.reserva.ms_reservas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients; // <--- Línea 1

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient// <--- Línea 2: ¡ESTO ES LO QUE FALTA!
public class MsReservasApplication {
	public static void main(String[] args) {
		SpringApplication.run(MsReservasApplication.class, args);
	}
}