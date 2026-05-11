package com.hotel.reserva.ms_huespedes.service;

import com.hotel.reserva.ms_huespedes.models.Huesped;
import com.hotel.reserva.ms_huespedes.repository.HuespedRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class HuespedServiceImpl implements IHuespedService {

    @Autowired
    private HuespedRepository repository;

    @Override
    public List<Huesped> listarTodos() {
        log.debug("Listando todos los huéspedes");
        return repository.findAll();
    }

    @Override
    public Optional<Huesped> buscarPorId(Long id) {
        log.debug("Buscando huésped con id: {}", id);
        return repository.findById(id);
    }

    @Override
    public Huesped guardar(Huesped huesped) {
        log.debug("Huésped guardado: {}", huesped.getNombre());
        return repository.save(huesped);
    }

    @Override
    public void eliminar(Long id) {
        log.debug("Eliminando huésped con id: {}", id);
        repository.deleteById(id);
    }

    @Override
    public Optional<Huesped> buscarPorDocumento(String documento) {
        log.debug("Buscando huésped con documento: {}", documento);
        return repository.findByDocumentoIdentidad(documento);
    }
}