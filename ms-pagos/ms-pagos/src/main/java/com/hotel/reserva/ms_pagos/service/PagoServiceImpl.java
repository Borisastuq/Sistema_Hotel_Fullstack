package com.hotel.reserva.ms_pagos.service;

import com.hotel.reserva.ms_pagos.models.Pago;
import com.hotel.reserva.ms_pagos.repository.PagoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
public class PagoServiceImpl implements IPagoService {

    @Autowired
    private PagoRepository repository;

    @Override
    public List<Pago> listarTodos() {
        log.debug("Listando todos los pagos");
        return repository.findAll();
    }

    @Override
    public Pago realizarPago(Pago pago) {
        log.debug("Realizando pago por monto: {}", pago.getMonto());
        return repository.save(pago);
    }

    @Override
    public List<Pago> buscarPorReserva(Long reservaId) {
        log.debug("Buscando pagos de reserva id: {}", reservaId);
        return repository.findByReservaId(reservaId);
    }
}