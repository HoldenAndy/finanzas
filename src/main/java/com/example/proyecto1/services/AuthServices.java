package com.example.proyecto1.services;

import com.example.proyecto1.daos.UsuarioDao;
import com.example.proyecto1.models.dtos.LoginPeticion;
import com.example.proyecto1.models.entities.Role;
import com.example.proyecto1.models.entities.Usuario;
import io.jsonwebtoken.Jwts;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServices {
    private final UsuarioDao usuarioDao;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthServices(UsuarioDao usuarioDao, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.usuarioDao = usuarioDao;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public void registrar(Usuario usuario){
        String encodedPassword = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(encodedPassword);

        if(usuario.getRole() == null){
            usuario.setRole(Role.USER);
        }
        usuarioDao.save(usuario);
    }

    public String login (LoginPeticion request){
        Usuario usuario = usuarioDao.findByEmail(request.getEmail()).orElseThrow(() ->
            new RuntimeException("Credenciales invalidas."));

        if(!passwordEncoder.matches(request.getPassword(), usuario.getPassword())){
            throw new RuntimeException("Credenciales inválidas");
        }

        return jwtService.generateToken(usuario);
    }

}
