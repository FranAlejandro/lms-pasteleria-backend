package com.pastelitosclau.lmspasteleria.repository;

import com.pastelitosclau.lmspasteleria.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Nota: no creamos OptionRepository a propósito. Las Option se guardan y se
 * leen a través de Question (cascade), así que no necesitan su propio acceso.
 */
public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByQuizId(Long quizId);
}
