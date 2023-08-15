
package com.api.tareas.repositorios;

import com.api.tareas.entidades.Tarea;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TareaRepository extends JpaRepository<Tarea,Long> {
   Page<Tarea> findAllByUsuarioUsuarioIdAndCompletadaFalse(Long usuarioId, Pageable pageable);
   Page<Tarea> findAllByUsuarioUsuarioIdAndCompletadaTrue(Long usuarioId, Pageable pageable);
}
