package com.hotel.reserva.ms_reservas.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "ms-habitaciones")
public interface HabitacionClient {

    // El que ya tenías: buscar habitación por id
    @GetMapping("/api/habitaciones/{id}")
    ResponseEntity<?> buscarPorId(@PathVariable("id") Long id);

    // NUEVO: cambiar el estado de una habitación
    @PutMapping("/api/habitaciones/{id}/estado")
    ResponseEntity<?> actualizarEstado(
            @PathVariable("id") Long id,
            @RequestParam("nuevoEstado") String nuevoEstado);
}