package com.hotel.reserva.ms_pagos.service;
import com.hotel.reserva.ms_pagos.models.Pago;
import java.util.List;

public interface IPagoService {
    List<Pago> listarTodos();
    Pago realizarPago(Pago pago);
    List<Pago> buscarPorReserva(Long reservaId);
}