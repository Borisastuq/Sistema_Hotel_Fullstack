package com.hotel.reserva.ms_habitaciones.controller;

import com.hotel.reserva.ms_habitaciones.models.Habitacion;
import com.hotel.reserva.ms_habitaciones.models.EstadoHabitacion;
import com.hotel.reserva.ms_habitaciones.service.IHabitacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/habitaciones")
@Tag(name = "Habitaciones", description = "Gestión de habitaciones del hotel: disponibilidad, ocupación y estado")
public class HabitacionController {

    @Autowired
    private IHabitacionService service;

    @GetMapping
    @Operation(summary = "Obtiene todas las habitaciones", description = "Retorna la lista completa de habitaciones registradas")
    public List<Habitacion> listar() { return service.listarTodas(); }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene una habitación por ID", description = "Retorna la habitación correspondiente al id enviado")
    public ResponseEntity<Habitacion> buscar(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/estado/{estado}")
    @Operation(summary = "Lista habitaciones por estado", description = "Retorna habitaciones filtradas por estado: DISPONIBLE u OCUPADA")
    public List<Habitacion> listarPorEstado(@PathVariable EstadoHabitacion estado) {
        return service.listarPorEstado(estado);
    }

    @PostMapping
    @Operation(summary = "Crea una nueva habitación", description = "Registra una habitación nueva en el sistema con estado inicial DISPONIBLE")
    public ResponseEntity<Habitacion> crear(@Valid @RequestBody Habitacion habitacion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(habitacion));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina una habitación", description = "Borra una habitación del sistema de forma permanente")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (service.buscarPorId(id).isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/estado")
    @Operation(summary = "Cambia el estado de una habitación", description = "Usado por ms-reservas via Feign para marcar OCUPADA o DISPONIBLE")
    public ResponseEntity<Habitacion> actualizarEstado(
            @PathVariable Long id,
            @RequestParam EstadoHabitacion nuevoEstado) {

        Habitacion habitacionActualizada = service.actualizarEstado(id, nuevoEstado);
        return ResponseEntity.ok(habitacionActualizada);
    }

}