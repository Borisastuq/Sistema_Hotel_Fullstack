package com.hotel.reserva.ms_reservas.service;

import com.hotel.reserva.ms_reservas.models.EstadoReserva;
import com.hotel.reserva.ms_reservas.models.Reserva;
import java.util.List;

public interface IReservaService {
    List<Reserva> listarTodas();
    Reserva buscarPorId(Long id);
    Reserva guardar(Reserva reserva);
    Reserva actualizar(Long id, Reserva reserva);
    void eliminar(Long id);
    Reserva cambiarEstado(Long id, EstadoReserva nuevoEstado);
    Reserva checkout(Long id);  // ← NUEVO
}