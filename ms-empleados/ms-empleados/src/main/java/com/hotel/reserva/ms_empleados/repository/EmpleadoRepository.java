package com.hotel.reserva.ms_empleados.repository;

import com.hotel.reserva.ms_empleados.models.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    List<Empleado> findByCargo(String cargo);
}