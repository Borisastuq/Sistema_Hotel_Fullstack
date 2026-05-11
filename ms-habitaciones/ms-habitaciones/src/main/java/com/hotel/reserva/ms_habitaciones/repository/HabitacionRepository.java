package com.hotel.reserva.ms_habitaciones.repository;

import com.hotel.reserva.ms_habitaciones.models.Habitacion;
import com.hotel.reserva.ms_habitaciones.models.EstadoHabitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {
    // Para buscar habitaciones libres rápidamente
    List<Habitacion> findByEstado(EstadoHabitacion estado);
}