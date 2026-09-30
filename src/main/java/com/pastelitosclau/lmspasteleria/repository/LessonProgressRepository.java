package com.pastelitosclau.lmspasteleria.repository;

import com.pastelitosclau.lmspasteleria.entity.LessonProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LessonProgressRepository extends JpaRepository<LessonProgress, Long> {

    Optional<LessonProgress> findByUserIdAndLessonId(Long userId, Long lessonId);

    // CU-08: lecciones completadas por el alumno dentro de un curso (numerador del %).
    long countByUserIdAndCompletadaTrueAndLessonModuleCourseId(Long userId, Long courseId);
}
