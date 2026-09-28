package com.pastelitosclau.lmspasteleria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * Nota de nombres: "Option" también es un nombre genérico que puede chocar
 * con otras clases (ej. java.util.Optional no, pero conviene tenerlo presente).
 * Si en algún momento el IDE se confunde, se puede renombrar a "QuestionOption".
 */
@Entity
@Table(name = "question_option")
@Getter
@Setter
@NoArgsConstructor
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @Column(nullable = false)
    private String texto;

    @Column(name = "es_correcta", nullable = false)
    private boolean esCorrecta = false;
}
