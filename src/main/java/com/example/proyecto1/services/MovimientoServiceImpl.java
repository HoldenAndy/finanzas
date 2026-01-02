package com.example.proyecto1.services;

import com.example.proyecto1.daos.MovimientoDao;
import com.example.proyecto1.daos.UsuarioDao;
import com.example.proyecto1.models.dtos.MovimientoPeticion;
import com.example.proyecto1.models.entities.Movimiento;
import com.example.proyecto1.models.entities.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MovimientoServiceImpl implements MovimientoService{

    @Value("${app.moneda.base}")
    private String monedaBase;

    private final MovimientoDao movimientoDao;
    private final UsuarioDao usuarioDao;
    private final CurrencyService currencyService;

    public MovimientoServiceImpl(MovimientoDao movimientoDao, UsuarioDao usuarioDao, CurrencyService currencyService) {
        this.movimientoDao = movimientoDao;
        this.usuarioDao = usuarioDao;
        this.currencyService = currencyService;
    }

    @Override
    @Transactional
    public void crearMovimiento(MovimientoPeticion request, String emailUsuario) {
        Usuario usuario = usuarioDao.findByEmail(emailUsuario).orElseThrow(() ->
            new RuntimeException("Usuario no encontrado"));
        BigDecimal montoConvertido = currencyService.convertir(request.monto(), request.moneda(), monedaBase);
        Movimiento movimiento = new Movimiento();
        movimiento.setUsuarioId(usuario.getId());
        movimiento.setCategoriaId(request.categoriaId());
        movimiento.setMonto(request.monto());
        movimiento.setMoneda(request.moneda());
        movimiento.setMontoBase(montoConvertido);
        movimiento.setDescripcion(request.descripcion());
        movimiento.setTipo(request.tipo());
        movimiento.setFecha(request.fecha() != null ? request.fecha() : LocalDate.now());
        movimientoDao.saveMovimiento(movimiento);
    }

    @Override
    @Transactional(readOnly=true)
    public List<Movimiento> listarMisMovimientos(String emailUsuario) {
        Usuario usuario = usuarioDao.findByEmail(emailUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return movimientoDao.findAllByUsuarioId(usuario.getId());
    }

    @Override
    @Transactional(readOnly=true)
    public Map<String, Object> obtenerResumenFinanciero(String email) {
        Usuario usuario = usuarioDao.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        BigDecimal saldoTotal = movimientoDao.calcularSaldoTotal(usuario.getId());

        Map<String, Object> resumen = new HashMap<>();
        resumen.put("saldoTotal", saldoTotal);
        resumen.put("usuario", usuario.getNombre());

        return resumen;
    }
}
