package com.pastelitosclau.lmspasteleria.repository;

import com.pastelitosclau.lmspasteleria.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModuleRepository extends JpaRepository<Module, Long> {

    // "CourseId" navega la relación: module.course.id
    List<Module> findByCourseIdOrderByOrdenAsc(Long courseId);
}
