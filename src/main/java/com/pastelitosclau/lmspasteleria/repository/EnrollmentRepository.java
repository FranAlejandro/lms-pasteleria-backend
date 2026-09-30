package com.pastelitosclau.lmspasteleria.repository;

import com.pastelitosclau.lmspasteleria.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    // CU-06: ¿este alumno está inscrito en este curso? Base de la autorización (403).
    boolean existsByUserIdAndCourseId(Long userId, Long courseId);

    // "Mis cursos" (GET /api/my-courses).
    List<Enrollment> findByUserId(Long userId);
}
