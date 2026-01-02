package com.example.proyecto1.categorias.daos;

import com.example.proyecto1.categorias.entities.Categoria;

import java.util.List;

public interface CategoriaDao {
        void insertarCategoriasPorDefecto(Long usuarioId);
        List<Categoria> findAllByUsuarioId(Long usuarioId);
    }
