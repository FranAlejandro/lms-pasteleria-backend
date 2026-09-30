package com.pastelitosclau.lmspasteleria.repository;

import com.pastelitosclau.lmspasteleria.entity.Course;
import com.pastelitosclau.lmspasteleria.entity.CourseStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    // CU-03: el catálogo público solo muestra cursos PUBLICADO.
    List<Course> findByEstado(CourseStatus estado);

    // Detalle público: un curso borrador no debe poder verse por su id.
    Optional<Course> findByIdAndEstado(Long id, CourseStatus estado);
}
