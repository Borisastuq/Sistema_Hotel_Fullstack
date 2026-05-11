package com.hotel.reserva.ms_habitaciones.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "habitaciones")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El número de habitación es obligatorio")
    @Column(unique = true)
    private String numero;

    @NotBlank(message = "El tipo de habitación (Simple, Doble, Suite) es obligatorio")
    private String tipo;

    @NotNull(message = "El precio por noche es obligatorio")
    @Positive(message = "El precio debe ser un valor positivo")
    private Double precioPorNoche;

    @Enumerated(EnumType.STRING) // Guarda el texto (DISPONIBLE) en la BD
    @NotNull(message = "El estado de la habitación es obligatorio")
    private EstadoHabitacion estado;
}