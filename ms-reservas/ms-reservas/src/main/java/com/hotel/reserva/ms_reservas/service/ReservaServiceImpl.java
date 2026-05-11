package com.hotel.reserva.ms_reservas.service;

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
        log.debug("Reserva guardada para habitación id: {}", reserva.getHabitacionId());
        return reservaRepository.save(reserva);
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
}