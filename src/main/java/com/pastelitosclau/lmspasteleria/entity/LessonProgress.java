package com.pastelitosclau.lmspasteleria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Un registro por cada (alumno, lección). Se usa para:
 * - CU-06: marcar una lección como completada.
 * - CU-08: calcular el % de progreso de un curso
 *   (lecciones completadas / total de lecciones del curso).
 */
@Entity
@Table(
    name = "lesson_progress",
    uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "lesson_id"})
)
@Getter
@Setter
@NoArgsConstructor
public class LessonProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id", nullable = false)
    private Lesson lesson;

    @Column(nullable = false)
    private boolean completada = false;

    @Column(name = "fecha_completado")
    private LocalDateTime fechaCompletado;

    /**
     * En vez de que el Controller o el Service seteen todos los campos
     * a mano, dejamos que la propia entidad se encargue de mantenerse
     * consistente: no puede quedar "completada = true" sin fecha,
     * ni "completada = false" con una fecha vieja colgando.
     */
    public void marcarCompletada() {
        this.completada = true;
        this.fechaCompletado = LocalDateTime.now();
    }
}
