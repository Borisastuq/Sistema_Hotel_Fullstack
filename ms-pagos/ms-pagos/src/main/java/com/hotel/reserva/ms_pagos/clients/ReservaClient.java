package com.hotel.reserva.ms_pagos.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "ms-reservas")
public interface ReservaClient {

    // Cambia el estado de una reserva (ej: a CONFIRMADA)
    @PutMapping("/api/reservas/{id}/estado")
    ResponseEntity<?> cambiarEstado(
            @PathVariable("id") Long id,
            @RequestParam("nuevoEstado") String nuevoEstado);
}