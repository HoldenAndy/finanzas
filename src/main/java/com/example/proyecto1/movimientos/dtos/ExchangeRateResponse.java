package com.example.proyecto1.movimientos.dtos;

import java.math.BigDecimal;
import java.util.Map;

public record ExchangeRateResponse(
        String result,
        String base_code,
        Map<String, BigDecimal> conversion_rates
) {}