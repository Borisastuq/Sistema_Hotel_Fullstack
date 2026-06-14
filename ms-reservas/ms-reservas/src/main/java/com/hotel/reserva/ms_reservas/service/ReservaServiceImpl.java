package com.hotel.reserva.ms_reservas.service;

import com.hotel.reserva.ms_reservas.clients.HabitacionClient;
import com.hotel.reserva.ms_reservas.models.EstadoReserva;
import com.hotel.reserva.ms_reservas.models.Reserva;
import com.hotel.reserva.ms_reservas.repository.ReservaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservaServiceImpl implements IReservaService {

    private final ReservaRepository reservaRepository;
    private final HabitacionClient habitacionClient;

    @Override
    public List<Reserva> listarTodas() {
        log.debug("Listando todas las reservas");
        return reservaRepository.findAll();
    }

    @Override
    public Reserva buscarPorId(Long id) {
        log.debug("Buscando reserva con id: {}", id);
        return reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada con ID: " + id));
    }

    @Override
    public Reserva guardar(Reserva reserva) {
        if (reserva.getEstado() == null) {
            reserva.setEstado(EstadoReserva.PENDIENTE);
        }

        Reserva reservaGuardada = reservaRepository.save(reserva);
        log.debug("Reserva guardada con id: {}", reservaGuardada.getId());

        try {
            habitacionClient.actualizarEstado(reserva.getHabitacionId(), "OCUPADA");
            log.debug("Habitación {} marcada como OCUPADA", reserva.getHabitacionId());
        } catch (Exception e) {
            log.error("Error al actualizar estado de habitación: {}", e.getMessage());
        }

        return reservaGuardada;
    }

    @Override
    public Reserva actualizar(Long id, Reserva reservaDetalles) {
        log.debug("Actualizando reserva con id: {}", id);
        Reserva reservaExistente = buscarPorId(id);
        reservaExistente.setFechaInicio(reservaDetalles.getFechaInicio());
        reservaExistente.setFechaFin(reservaDetalles.getFechaFin());
        reservaExistente.setEstado(reservaDetalles.getEstado());
        reservaExistente.setHabitacionId(reservaDetalles.getHabitacionId());
        reservaExistente.setHuespedId(reservaDetalles.getHuespedId());
        return reservaRepository.save(reservaExistente);
    }

    @Override
    public void eliminar(Long id) {
        log.debug("Eliminando reserva con id: {}", id);
        Reserva reserva = buscarPorId(id);
        reservaRepository.delete(reserva);
    }
    @Override
    public Reserva cambiarEstado(Long id, EstadoReserva nuevoEstado) {
        Reserva reserva = buscarPorId(id);
        reserva.setEstado(nuevoEstado);
        log.debug("Reserva {} cambiada a estado: {}", id, nuevoEstado);
        return reservaRepository.save(reserva);
    }
    @Override
    public Reserva checkout(Long id) {
        // 1. Buscamos la reserva
        Reserva reserva = buscarPorId(id);

        // 2. Cambiamos la reserva a CANCELADA
        reserva.setEstado(EstadoReserva.CANCELADA);
        Reserva reservaActualizada = reservaRepository.save(reserva);
        log.debug("Reserva {} marcada como CANCELADA (checkout)", id);

        // 3. Avisamos a ms-habitaciones que la habitación vuelve a DISPONIBLE
        try {
            habitacionClient.actualizarEstado(reserva.getHabitacionId(), "DISPONIBLE");
            log.debug("Habitación {} liberada a DISPONIBLE", reserva.getHabitacionId());
        } catch (Exception e) {
            log.error("Error al liberar habitación: {}", e.getMessage());
        }

        return reservaActualizada;
    }
}