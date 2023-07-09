
package com.api.tareas.controladores;

import com.api.tareas.entidades.Usuario;
import com.api.tareas.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(Usuario usuario){
         usuarioService.guardarUsuario(usuario);
         return ResponseEntity.ok(usuario);
    }
    
    
}
