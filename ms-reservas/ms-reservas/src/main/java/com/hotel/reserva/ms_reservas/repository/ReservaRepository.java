package com.hotel.reserva.ms_reservas.repository;

import com.hotel.reserva.ms_reservas.models.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    // Buscar reservas por huésped
    List<Reserva> findByHuespedId(Long huespedId);
}