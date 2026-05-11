package com.hotel.reserva.ms_pagos.controller;

import com.hotel.reserva.ms_pagos.models.Pago;
import com.hotel.reserva.ms_pagos.service.IPagoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    @Autowired
    private IPagoService service;

    @GetMapping
    public List<Pago> listar() { return service.listarTodos(); }

    @PostMapping
    public ResponseEntity<Pago> crear(@Valid @RequestBody Pago pago) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.realizarPago(pago));
    }

    @GetMapping("/reserva/{reservaId}")
    public List<Pago> buscarPorReserva(@PathVariable Long reservaId) {
        return service.buscarPorReserva(reservaId);
    }
}