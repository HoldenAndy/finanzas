package com.example.proyecto1.auth.services;

import com.example.proyecto1.auth.dtos.AuthResponse;
import com.example.proyecto1.auth.dtos.LoginPeticion;
import com.example.proyecto1.auth.dtos.RegisterPeticion;

public interface AuthService {
    void registrar(RegisterPeticion request);
    public AuthResponse login (LoginPeticion request);
    public boolean activarCuenta(String codigo);
}
