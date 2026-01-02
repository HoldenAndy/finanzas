package com.example.proyecto1.controllers;

import com.example.proyecto1.models.dtos.MovimientoPeticion;
import com.example.proyecto1.services.MovimientoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/movimientos")
public class MovimientosController {
    private final MovimientoService movimientoService;

    public MovimientosController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @PostMapping
    public ResponseEntity<?> crearMovimiento(@Valid @RequestBody MovimientoPeticion request, Principal principal) {
        movimientoService.crearMovimiento(request, principal.getName());
        return ResponseEntity.ok(Map.of("mensaje", "Movimiento registrado con éxito"));
    }

    @GetMapping
    public ResponseEntity<?> listarMovimientos(Principal principal) {
        return ResponseEntity.ok(movimientoService.listarMisMovimientos(principal.getName()));
    }

    @GetMapping("/balance")
    public ResponseEntity<?> obtenerBalance(Principal principal) {
        // Usamos el email del token para saber de quién es el balance
        return ResponseEntity.ok(movimientoService.obtenerResumenFinanciero(principal.getName()));
    }
}
