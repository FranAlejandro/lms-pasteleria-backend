package com.pastelitosclau.lmspasteleria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/*
 * Nota de nombres: "Module" choca con java.lang.Module (Java 9+ Module System).
 * No da error porque estamos en el mismo paquete y el import es explícito por FQCN
 * cuando hace falta, pero si algún día ves un IDE confundido, es por esto.
 * Alternativa más segura en proyectos reales: llamarla "CourseModule".
 */
@Entity
@Table(name = "course_module")
@Getter
@Setter
@NoArgsConstructor
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Lado "dueño" de la relación: esta columna (course_id) es la que
    // realmente existe en la tabla. FetchType.LAZY significa que,
    // al cargar un Module, Course NO se carga automáticamente de la base de datos
    // hasta que llames a module.getCourse() explícitamente. Esto evita
    // cargar datos que no necesitas y es la práctica recomendada para @ManyToOne.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private Integer orden;

    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("orden ASC")
    private List<Lesson> lessons = new ArrayList<>();
}
