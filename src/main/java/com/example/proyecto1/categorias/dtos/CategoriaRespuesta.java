package com.example.proyecto1.categorias.dtos;

import com.example.proyecto1.movimientos.entities.TipoMovimiento;

public record CategoriaRespuesta(
        Long id,
        String nombre,
        TipoMovimiento tipo
) {}