package com.hotel.reserva.ms_reservas.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotel.reserva.ms_reservas.models.EstadoReserva;
import com.hotel.reserva.ms_reservas.models.Reserva;
import com.hotel.reserva.ms_reservas.service.IReservaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReservaController.class)
public class ReservaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IReservaService reservaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testBuscarPorId() throws Exception {
        Reserva reserva = crearReserva();
        when(reservaService.buscarPorId(1L)).thenReturn(reserva);

        mockMvc.perform(get("/api/reservas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.estado").value("PENDIENTE"));
    }

    @Test
    public void testCrearReserva() throws Exception {
        Reserva reserva = crearReserva();
        when(reservaService.guardar(any(Reserva.class))).thenReturn(reserva);

        mockMvc.perform(post("/api/reservas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reserva)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.estado").value("PENDIENTE"));
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