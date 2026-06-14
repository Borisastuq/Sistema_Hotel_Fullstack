package com.hotel.reserva.ms_pagos.service;

import com.hotel.reserva.ms_pagos.clients.ReservaClient;
import com.hotel.reserva.ms_pagos.models.Pago;
import com.hotel.reserva.ms_pagos.repository.PagoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PagoServiceImpl implements IPagoService {

    private final PagoRepository repository;
    private final ReservaClient reservaClient;

    @Override
    public List<Pago> listarTodos() {
        log.debug("Listando todos los pagos");
        return repository.findAll();
    }

    @Override
    public Pago realizarPago(Pago pago) {
        // 1. Guardamos el pago en nuestra base de datos
        Pago pagoGuardado = repository.save(pago);
        log.debug("Pago registrado con id: {}", pagoGuardado.getId());

        // 2. Avisamos a ms-reservas que cambie el estado a CONFIRMADA
        try {
            reservaClient.cambiarEstado(pago.getReservaId(), "CONFIRMADA");
            log.debug("Reserva {} confirmada tras el pago", pago.getReservaId());
        } catch (Exception e) {
            log.error("Error al confirmar reserva: {}", e.getMessage());
        }

        return pagoGuardado;
    }

    @Override
    public List<Pago> buscarPorReserva(Long reservaId) {
        log.debug("Buscando pagos de reserva id: {}", reservaId);
        return repository.findByReservaId(reservaId);
    }
}