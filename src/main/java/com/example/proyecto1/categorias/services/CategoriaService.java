package com.example.proyecto1.categorias.services;

import com.example.proyecto1.categorias.dtos.CategoriaRespuesta;

import java.util.List;

public interface CategoriaService {
    List<CategoriaRespuesta> listarPorUsuario(String email);
}
