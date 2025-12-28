package com.example.proyecto1.daos;

import com.example.proyecto1.models.entities.Usuario;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UsuarioDao {
    void save(Usuario usuario);
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> buscarPorCodigoVerificacion(String codigo);
    void ActivarUsuario(Long id);
}
