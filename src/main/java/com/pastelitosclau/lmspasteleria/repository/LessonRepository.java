package com.pastelitosclau.lmspasteleria.repository;

import com.pastelitosclau.lmspasteleria.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findByModuleIdOrderByOrdenAsc(Long moduleId);

    // Total de lecciones de un curso (navega lesson.module.course.id).
    // CU-08: es el denominador del porcentaje de progreso.
    long countByModuleCourseId(Long courseId);
}
