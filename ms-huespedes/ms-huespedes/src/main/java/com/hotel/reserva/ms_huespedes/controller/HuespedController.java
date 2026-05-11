package com.hotel.reserva.ms_huespedes.controller;

import com.hotel.reserva.ms_huespedes.models.Huesped;
import com.hotel.reserva.ms_huespedes.service.IHuespedService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/huespedes")
public class HuespedController {

    @Autowired
    private IHuespedService service;

    @GetMapping
    public List<Huesped> listar() { return service.listarTodos(); }

    @GetMapping("/{id}")
    public ResponseEntity<Huesped> buscar(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Huesped> crear(@Valid @RequestBody Huesped huesped) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(huesped));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (service.buscarPorId(id).isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}