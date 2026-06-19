package com.hotel.reserva.ms_reservas.controller;

import com.hotel.reserva.ms_reservas.models.Reserva;
import com.hotel.reserva.ms_reservas.service.IReservaService;
import com.hotel.reserva.ms_reservas.models.EstadoReserva;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
@RequiredArgsConstructor
@Tag(name = "Reservas", description = "Gestión de reservas del hotel: creación, consulta, cambio de estado y checkout")
public class ReservaController {

    private final IReservaService reservaService;

    // 1. LEER TODAS
    @GetMapping
    @Operation(summary = "Obtiene todas las reservas", description = "Retorna la lista completa de reservas registradas en el sistema")
    public List<Reserva> listar() {
        return reservaService.listarTodas();
    }

    // 2. BUSCAR POR ID
    @GetMapping("/{id}")
    @Operation(summary = "Obtiene una reserva por ID", description = "Retorna la reserva correspondiente al id enviado")
    public ResponseEntity<Reserva> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.buscarPorId(id));
    }

    // 3. CREAR
    @PostMapping
    @Operation(summary = "Crea una nueva reserva", description = "Guarda la reserva y cambia automáticamente el estado de la habitación a OCUPADA")
    public ResponseEntity<Reserva> crear(@RequestBody Reserva reserva) {
        return ResponseEntity.status(201).body(reservaService.guardar(reserva));
    }

    // 4. ACTUALIZAR
    @PutMapping("/{id}")
    @Operation(summary = "Actualiza una reserva", description = "Modifica los datos de una reserva existente")
    public ResponseEntity<Reserva> actualizar(@PathVariable Long id, @RequestBody Reserva reservaDetalles) {
        return ResponseEntity.ok(reservaService.actualizar(id, reservaDetalles));
    }

    // 5. ELIMINAR
    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina una reserva", description = "Borra una reserva del sistema de forma permanente")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        reservaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // 6. CAMBIAR ESTADO
    @PutMapping("/{id}/estado")
    @Operation(summary = "Cambia el estado de una reserva", description = "Usado por ms-pagos para confirmar una reserva tras registrar el pago")
    public ResponseEntity<Reserva> cambiarEstado(
            @PathVariable Long id,
            @RequestParam EstadoReserva nuevoEstado) {

        Reserva reservaActualizada = reservaService.cambiarEstado(id, nuevoEstado);
        return ResponseEntity.ok(reservaActualizada);
    }

    // 7. CHECKOUT
    @PutMapping("/{id}/checkout")
    @Operation(summary = "Realiza el checkout de una reserva", description = "Cancela la reserva y libera la habitación, dejándola DISPONIBLE")
    public ResponseEntity<Reserva> checkout(@PathVariable Long id) {
        Reserva reserva = reservaService.checkout(id);
        return ResponseEntity.ok(reserva);
    }
}