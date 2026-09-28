package com.pastelitosclau.lmspasteleria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Representa un usuario del sistema (Admin o Alumno).
 *
 * @Entity le dice a JPA "esta clase es una tabla".
 * @Table es opcional; si no la pones, JPA usa el nombre de la clase.
 * La ponemos igual porque "user" es palabra reservada en algunos motores SQL,
 * así que la tabla se llamará "app_user" para evitar conflictos con PostgreSQL.
 */
@Entity
@Table(name = "app_user")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    // unique = true crea una restricción UNIQUE en la base de datos:
    // dos usuarios no pueden registrarse con el mismo correo (ver CU-01).
    @Column(nullable = false, unique = true)
    private String correo;

    // Guardamos el hash de la contraseña (BCrypt), nunca la contraseña en texto plano.
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    // @Enumerated(EnumType.STRING) guarda el rol como texto ("ADMIN", "ALUMNO")
    // en vez de un número (0, 1). Es más legible si algún día miras la tabla directamente,
    // y no se rompe si en el futuro reordenas el enum.
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role rol;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    // Este método se ejecuta automáticamente ANTES de que Hibernate
    // inserte el registro por primera vez. Así no dependemos de que
    // el Service se acuerde de setear la fecha manualmente.
    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
    }
}
