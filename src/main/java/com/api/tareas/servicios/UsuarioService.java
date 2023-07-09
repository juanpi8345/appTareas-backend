
package com.api.tareas.servicios;

import com.api.tareas.entidades.Usuario;

public interface UsuarioService {
    Usuario obtenerUsuarioPorId(Long usuarioId);
    Usuario guardarUsuario(Usuario usuario);
}
