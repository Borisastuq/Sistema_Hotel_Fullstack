package com.hotel.reserva.ms_huespedes.repository;

import com.hotel.reserva.ms_huespedes.models.Huesped;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface HuespedRepository extends JpaRepository<Huesped, Long> {
    // Método extra para buscar por documento (útil para validaciones)
    Optional<Huesped> findByDocumentoIdentidad(String documentoIdentidad);
}