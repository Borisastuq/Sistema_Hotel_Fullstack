package com.hotel.reserva.ms_habitaciones.service;

import com.hotel.reserva.ms_habitaciones.models.Habitacion;
import com.hotel.reserva.ms_habitaciones.models.EstadoHabitacion;
import com.hotel.reserva.ms_habitaciones.repository.HabitacionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class HabitacionServiceImpl implements IHabitacionService {

    @Autowired
    private HabitacionRepository repository;

    @Override
    public List<Habitacion> listarTodas() {
        log.debug("Listando todas las habitaciones");
        return repository.findAll();
    }

    @Override
    public Optional<Habitacion> buscarPorId(Long id) {
        log.debug("Buscando habitación con id: {}", id);
        return repository.findById(id);
    }

    @Override
    public Habitacion guardar(Habitacion h) {
        log.debug("Habitación guardada: {}", h.getNumero());
        return repository.save(h);
    }

    @Override
    public void eliminar(Long id) {
        log.debug("Eliminando habitación con id: {}", id);
        repository.deleteById(id);
    }

    @Override
    public List<Habitacion> listarPorEstado(EstadoHabitacion estado) {
        log.debug("Buscando habitaciones con estado: {}", estado);
        return repository.findByEstado(estado);
    }

    @Override
    public Habitacion actualizarEstado(Long id, EstadoHabitacion nuevoEstado) {
        Habitacion habitacion = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Habitación no encontrada con id: " + id));

        habitacion.setEstado(nuevoEstado);

        log.debug("Habitación {} cambiada a estado: {}", id, nuevoEstado);
        return repository.save(habitacion);

    }
}