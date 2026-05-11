package com.hotel.reserva.ms_empleados.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "empleados")
@Data
@AllArgsConstructor // Genera constructor con todos los campos
@NoArgsConstructor  // Genera constructor vacío (Vital para JPA)
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El cargo es obligatorio")
    private String cargo;

    @NotBlank(message = "El documento de identidad es obligatorio")
    private String documentoIdentidad;

    @NotNull(message = "El sueldo no puede estar vacío")
    @Positive(message = "El sueldo debe ser un valor positivo")
    private Double sueldo;
}