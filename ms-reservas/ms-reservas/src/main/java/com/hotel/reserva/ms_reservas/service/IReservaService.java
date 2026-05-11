package com.hotel.reserva.ms_reservas.service;

import com.hotel.reserva.ms_reservas.models.Reserva;
import java.util.List;

public interface IReservaService {
    List<Reserva> listarTodas();
    Reserva buscarPorId(Long id); // Para el detalle de una reserva
    Reserva guardar(Reserva reserva);
    Reserva actualizar(Long id, Reserva reserva); // Para modificar fechas o estado
    void eliminar(Long id); // Para cancelar o borrar
}