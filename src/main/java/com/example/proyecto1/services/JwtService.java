package com.example.proyecto1.services;

import com.example.proyecto1.models.entities.Usuario;

public interface JwtService {
    String generateToken(Usuario usuario);
    String extracUsername(String token);
    boolean isTokenValid(String token, Usuario usuario);
    boolean isTokenExpired(String token);
}
