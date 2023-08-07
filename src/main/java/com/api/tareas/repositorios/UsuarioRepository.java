
package com.api.tareas.repositorios;

import com.api.tareas.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
    
    Usuario findByUsername(String username);
    
}
