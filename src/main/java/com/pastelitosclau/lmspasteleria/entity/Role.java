package com.pastelitosclau.lmspasteleria.entity;

/**
 * Roles del sistema. En la Fase 1 solo hay dos.
 * Usamos un enum en vez de un String libre en la base de datos
 * para que sea imposible guardar un rol que no exista (ej: "ADMINN" por typo).
 */
public enum Role {
    ADMIN,
    ALUMNO
}
