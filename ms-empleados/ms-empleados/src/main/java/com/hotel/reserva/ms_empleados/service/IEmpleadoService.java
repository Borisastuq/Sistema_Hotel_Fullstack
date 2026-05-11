package com.hotel.reserva.ms_empleados.service;

import com.hotel.reserva.ms_empleados.models.Empleado;
import java.util.List;
import java.util.Optional;

public interface IEmpleadoService {
    List<Empleado> listarTodos();
    Optional<Empleado> buscarPorId(Long id);
    Empleado guardar(Empleado empleado);
    void eliminar(Long id);
    List<Empleado> buscarPorCargo(String cargo);
}