package com.hotel.reserva.ms_reservas.controller;

import com.hotel.reserva.ms_reservas.models.Reserva;
import com.hotel.reserva.ms_reservas.service.IReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hotel.reserva.ms_reservas.models.EstadoReserva;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
@RequiredArgsConstructor
public class ReservaController {

    private final IReservaService reservaService;

    // 1. LEER TODAS (Ya lo tienes)
    @GetMapping
    public List<Reserva> listar() {
        return reservaService.listarTodas();
    }

    // 2. BUSCAR POR ID (Nuevo)
    @GetMapping("/{id}")
    public ResponseEntity<Reserva> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.buscarPorId(id));
    }

    // 3. CREAR (Actualizado con @RequestBody)
    @PostMapping
    public ResponseEntity<Reserva> crear(@RequestBody Reserva reserva) {
        return ResponseEntity.status(201).body(reservaService.guardar(reserva));
    }

    // 4. ACTUALIZAR (Nuevo)
    @PutMapping("/{id}")
    public ResponseEntity<Reserva> actualizar(@PathVariable Long id, @RequestBody Reserva reservaDetalles) {
        return ResponseEntity.ok(reservaService.actualizar(id, reservaDetalles));
    }

    // 5. ELIMINAR (Nuevo)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        reservaService.eliminar(id);
        return ResponseEntity.noContent().build(); // Devuelve 204 No Content si se borró bien
    }

    // 6. CAMBIAR ESTADO (Para que ms-pagos confirme la reserva)
    @PutMapping("/{id}/estado")
    public ResponseEntity<Reserva> cambiarEstado(
            @PathVariable Long id,
            @RequestParam EstadoReserva nuevoEstado) {

        Reserva reservaActualizada = reservaService.cambiarEstado(id, nuevoEstado);
        return ResponseEntity.ok(reservaActualizada);
    }

    // 7. CHECKOUT → habitación vuelve a DISPONIBLE
    @PutMapping("/{id}/checkout")
    public ResponseEntity<Reserva> checkout(@PathVariable Long id) {
        Reserva reserva = reservaService.checkout(id);
        return ResponseEntity.ok(reserva);
    }
}