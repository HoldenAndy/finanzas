package com.example.proyecto1.currencies.services;

import com.example.proyecto1.movimientos.entities.Moneda;

import java.math.BigDecimal;

public interface CurrencyService {
    BigDecimal convertir(BigDecimal monto, Moneda origen, String destino);
}
