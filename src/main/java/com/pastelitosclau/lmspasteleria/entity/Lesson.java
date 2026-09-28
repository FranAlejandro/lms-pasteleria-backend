package com.pastelitosclau.lmspasteleria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "lesson")
@Getter
@Setter
@NoArgsConstructor
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_id", nullable = false)
    private Module module;

    @Column(nullable = false)
    private String titulo;

    // En la Fase 1 esto es solo una URL a un archivo guardado localmente o en S3.
    // En la Fase 3 este mismo campo apuntará a una URL de Bunny.net con domain-lock,
    // pero el modelo de datos no cambia — solo cambia de dónde viene la URL.
    @Column(name = "video_url")
    private String videoUrl;

    @Column(name = "material_url")
    private String materialUrl;

    @Column(nullable = false)
    private Integer orden;

    /*
     * Relación 1 a 1 opcional: una Lesson tiene A LO MÁS un Quiz.
     * mappedBy indica que el dueño de la relación es Quiz (tiene el FK lesson_id).
     * optional = true (default) permite que una lección no tenga quiz.
     */
    @OneToOne(mappedBy = "lesson", cascade = CascadeType.ALL, orphanRemoval = true)
    private Quiz quiz;
}
