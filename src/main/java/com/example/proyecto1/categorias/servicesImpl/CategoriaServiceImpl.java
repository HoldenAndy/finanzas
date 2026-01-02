package com.example.proyecto1.categorias.servicesImpl;

import com.example.proyecto1.categorias.daos.CategoriaDao;
import com.example.proyecto1.categorias.services.CategoriaService;
import com.example.proyecto1.usuarios.daos.UsuarioDao;
import com.example.proyecto1.categorias.dtos.CategoriaRespuesta;
import com.example.proyecto1.categorias.entities.Categoria;
import com.example.proyecto1.usuarios.entities.Usuario;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {
    private final CategoriaDao categoriaDao;
    private final UsuarioDao usuarioDao; // Para buscar el ID por email

    public CategoriaServiceImpl(CategoriaDao categoriaDao, UsuarioDao usuarioDao) {
        this.categoriaDao = categoriaDao;
        this.usuarioDao = usuarioDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoriaRespuesta> listarPorUsuario(String email) {
        Usuario usuario = usuarioDao.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        List<Categoria> categoriasEntity = categoriaDao.findAllByUsuarioId(usuario.getId());
        return categoriasEntity.stream()
                .map(cat -> new CategoriaRespuesta(
                        cat.getId(),
                        cat.getNombre(),
                        cat.getTipo()
                ))
                .toList();
    }
}