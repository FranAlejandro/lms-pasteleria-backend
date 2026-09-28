package com.pastelitosclau.lmspasteleria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
@Getter
@Setter
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "precio_clp", nullable = false)
    private Integer precioClp;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CourseStatus estado = CourseStatus.BORRADOR;

    @Column(name = "imagen_portada_url")
    private String imagenPortadaUrl;

    /*
     * Relación 1 Course -> muchos Module.
     *
     * mappedBy = "course" significa: "la relación ya está definida del otro lado,
     * en el campo 'course' de la clase Module. No crees una columna extra acá".
     * La FK (course_id) vive en la tabla module, no en course.
     *
     * cascade = CascadeType.ALL: si borras un Course, se borran también sus Module
     * (tiene sentido en este dominio: un módulo no existe sin su curso).
     *
     * orphanRemoval = true: si sacas un Module de esta lista en código,
     * se borra de la base de datos (no se queda "huérfano").
     */
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("orden ASC")
    private List<Module> modules = new ArrayList<>();
}
