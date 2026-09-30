package com.pastelitosclau.lmspasteleria.repository;

import com.pastelitosclau.lmspasteleria.entity.QuizAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizAttemptRepository extends JpaRepository<QuizAttempt, Long> {

    List<QuizAttempt> findByUserIdAndQuizIdOrderByFechaDesc(Long userId, Long quizId);
}
