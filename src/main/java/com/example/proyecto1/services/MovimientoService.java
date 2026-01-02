package com.example.proyecto1.services;

import com.example.proyecto1.models.dtos.MovimientoPeticion;
import com.example.proyecto1.models.entities.Movimiento;

import java.util.List;
import java.util.Map;

public interface MovimientoService {
    void crearMovimiento(MovimientoPeticion request, String emailUsuario);
    List<Movimiento> listarMisMovimientos(String emailUsuario);
    Map<String, Object> obtenerResumenFinanciero(String email);
}
