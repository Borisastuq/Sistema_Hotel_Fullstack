package com.hotel.reserva.ms_habitaciones.service;

import com.hotel.reserva.ms_habitaciones.models.Habitacion;
import com.hotel.reserva.ms_habitaciones.models.EstadoHabitacion;
import java.util.List;
import java.util.Optional;

public interface IHabitacionService {
    List<Habitacion> listarTodas();
    Optional<Habitacion> buscarPorId(Long id);
    Habitacion guardar(Habitacion habitacion);
    void eliminar(Long id);
    List<Habitacion> listarPorEstado(EstadoHabitacion estado);
}