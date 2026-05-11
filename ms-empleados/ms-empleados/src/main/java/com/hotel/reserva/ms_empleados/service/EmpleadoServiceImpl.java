package com.hotel.reserva.ms_empleados.service;

import com.hotel.reserva.ms_empleados.models.Empleado;
import com.hotel.reserva.ms_empleados.repository.EmpleadoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmpleadoServiceImpl implements IEmpleadoService {

    @Autowired
    private EmpleadoRepository repository;

    @Override
    public List<Empleado> listarTodos() {
        log.debug("Listando todos los empleados");
        return repository.findAll();
    }

    @Override
    public Optional<Empleado> buscarPorId(Long id) {
        log.debug("Buscando empleado con id: {}", id);
        return repository.findById(id);
    }

    @Override
    public Empleado guardar(Empleado empleado) {
        log.debug("Empleado guardado: {}", empleado.getNombre());
        return repository.save(empleado);
    }

    @Override
    public void eliminar(Long id) {
        log.debug("Eliminando empleado con id: {}", id);
        repository.deleteById(id);
    }

    @Override
    public List<Empleado> buscarPorCargo(String cargo) {
        log.debug("Buscando empleados con cargo: {}", cargo);
        return repository.findByCargo(cargo);
    }
}