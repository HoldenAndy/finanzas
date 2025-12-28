package com.example.proyecto1.services;

import com.example.proyecto1.models.dtos.AuthResponse;
import com.example.proyecto1.models.dtos.LoginPeticion;
import com.example.proyecto1.models.dtos.RegisterPeticion;

public interface AuthService {
    void registrar(RegisterPeticion request);
    public AuthResponse login (LoginPeticion request);
    public boolean activarCuenta(String codigo);
}
