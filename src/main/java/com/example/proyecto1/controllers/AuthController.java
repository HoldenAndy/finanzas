package com.example.proyecto1.controllers;

import com.example.proyecto1.models.dtos.AuthResponse;
import com.example.proyecto1.models.dtos.LoginPeticion;
import com.example.proyecto1.models.entities.Usuario;
import com.example.proyecto1.services.AuthServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthServices authServices;

    public AuthController(AuthServices authServices) {
        this.authServices = authServices;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registrar(@RequestBody Usuario usuario){
        try {
            authServices.registrar(usuario);
            Map<String, String> respuesta = new HashMap<>();
            respuesta.put("mensaje", "Registro de usuario éxitoso!");
            return ResponseEntity.ok(respuesta.toString());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginPeticion loginPeticion){
        try {
            String token = authServices.login(loginPeticion);
            return ResponseEntity.ok(new AuthResponse(token));
        }catch (Exception e){
            Map<String, String> error = new HashMap<>();
            error.put("error", "Credenciales incorrectas");
            return ResponseEntity.status(401).body(error);
        }
    }
}
