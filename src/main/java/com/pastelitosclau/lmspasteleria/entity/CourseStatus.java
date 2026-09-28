package com.pastelitosclau.lmspasteleria.entity;

/**
 * Estado de publicación de un curso.
 * Un curso en BORRADOR no debe aparecer en el catálogo público (CU-03).
 */
public enum CourseStatus {
    BORRADOR,
    PUBLICADO
}
