package com.hotel.reserva.ms_reservas.service;

import com.hotel.reserva.ms_reservas.clients.HabitacionClient;
import com.hotel.reserva.ms_reservas.models.EstadoReserva;
import com.hotel.reserva.ms_reservas.models.Reserva;
import com.hotel.reserva.ms_reservas.repository.ReservaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class ReservaServiceTest {

    @Autowired
    private IReservaService reservaService;

    @MockBean
    private ReservaRepository reservaRepository;

    @MockBean
    private HabitacionClient habitacionClient;

    @Test
    public void testBuscarPorId() {
        Long id = 1L;
        Reserva reserva = crearReserva();
        when(reservaRepository.findById(id)).thenReturn(Optional.of(reserva));

        Reserva encontrada = reservaService.buscarPorId(id);

        assertNotNull(encontrada);
        assertEquals(id, encontrada.getId());
    }

    @Test
    public void testGuardarReserva() {
        Reserva reserva = crearReserva();
        reserva.setEstado(null);

        when(reservaRepository.save(reserva)).thenReturn(reserva);
        when(habitacionClient.actualizarEstado(reserva.getHabitacionId(), "OCUPADA"))
                .thenReturn(ResponseEntity.ok().build());

        Reserva guardada = reservaService.guardar(reserva);

        assertNotNull(guardada);
        assertEquals(EstadoReserva.PENDIENTE, guardada.getEstado());
    }

    public Reserva crearReserva() {
        Reserva reserva = new Reserva();
        reserva.setId(1L);
        reserva.setFechaInicio(LocalDate.of(2026, 6, 15));
        reserva.setFechaFin(LocalDate.of(2026, 6, 20));
        reserva.setHabitacionId(2L);
        reserva.setHuespedId(1L);
        reserva.setEstado(EstadoReserva.PENDIENTE);
        return reserva;
    }
}