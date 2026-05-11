package com.hotel.reserva.ms_huespedes.service;

import com.hotel.reserva.ms_huespedes.models.Huesped;
import java.util.List;
import java.util.Optional;

public interface IHuespedService {
    List<Huesped> listarTodos();
    Optional<Huesped> buscarPorId(Long id);
    Huesped guardar(Huesped huesped);
    void eliminar(Long id);
    Optional<Huesped> buscarPorDocumento(String documento);
}