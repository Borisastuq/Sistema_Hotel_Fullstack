package com.hotel.reserva.ms_habitaciones.controller;

import com.hotel.reserva.ms_habitaciones.models.Habitacion;
import com.hotel.reserva.ms_habitaciones.models.EstadoHabitacion;
import com.hotel.reserva.ms_habitaciones.service.IHabitacionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/habitaciones")
public class HabitacionController {

    @Autowired
    private IHabitacionService service;

    @GetMapping
    public List<Habitacion> listar() { return service.listarTodas(); }

    @GetMapping("/{id}")
    public ResponseEntity<Habitacion> buscar(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/estado/{estado}")
    public List<Habitacion> listarPorEstado(@PathVariable EstadoHabitacion estado) {
        return service.listarPorEstado(estado);
    }

    @PostMapping
    public ResponseEntity<Habitacion> crear(@Valid @RequestBody Habitacion habitacion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(habitacion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (service.buscarPorId(id).isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Habitacion> actualizarEstado(
            @PathVariable Long id,
            @RequestParam EstadoHabitacion nuevoEstado) {

        Habitacion habitacionActualizada = service.actualizarEstado(id, nuevoEstado);
        return ResponseEntity.ok(habitacionActualizada);
    }

}