
package com.api.tareas.servicios;

import com.api.tareas.entidades.Usuario;
import com.api.tareas.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario obtenerUsuarioPorId(Long usuarioId) {
       return usuarioRepository.findById(usuarioId).orElse(null);
    }

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
       return usuarioRepository.save(usuario);
    }
    
}
