package com.hotel.reserva.ms_reservas.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;

@FeignClient(name = "ms-habitaciones") // Debe coincidir con el nombre en Eureka
public interface HabitacionClient {
    @GetMapping("/api/habitaciones/{id}")
    ResponseEntity<?> buscarPorId(@PathVariable("id") Long id);
}