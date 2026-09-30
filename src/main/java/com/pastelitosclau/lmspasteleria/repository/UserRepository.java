package com.pastelitosclau.lmspasteleria.repository;

import com.pastelitosclau.lmspasteleria.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * JpaRepository<User, Long>: "un repositorio de entidades User cuyo id es Long".
 * Con solo declarar esta interfaz, Spring genera save(), findById(), findAll(), etc.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    // Spring lo traduce a: SELECT * FROM app_user WHERE correo = ?
    // Optional porque el usuario puede no existir (login con correo inexistente).
    Optional<User> findByCorreo(String correo);

    // Lo usaremos en CU-01 para responder 409 si el correo ya está registrado.
    boolean existsByCorreo(String correo);
}
