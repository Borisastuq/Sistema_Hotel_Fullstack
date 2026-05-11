package com.hotel.reserva.ms_pagos.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El ID de la reserva es obligatorio")
    private Long reservaId; // Relación lógica con ms-reservas

    @NotNull(message = "El monto es obligatorio")
    @Positive(message = "El monto debe ser mayor a cero")
    private Double monto;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "El método de pago es obligatorio")
    private MetodoPago metodoPago;

    private LocalDateTime fechaPago;

    @PrePersist
    protected void onCreate() {
        fechaPago = LocalDateTime.now(); // Asigna la fecha actual automáticamente
    }
}