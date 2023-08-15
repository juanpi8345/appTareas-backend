
package com.api.tareas.repositorios;

import com.api.tareas.entidades.Tarea;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TareaRepository extends JpaRepository<Tarea,Long> {
   Page<Tarea> findAllByUsuarioUsuarioIdAndCompletadaFalseAndCaducadaFalse(Long usuarioId, Pageable pageable);
   List<Tarea> findAllByUsuarioUsuarioIdAndCompletadaFalseAndCaducadaFalse(Long usuarioId);
   Page<Tarea> findAllByUsuarioUsuarioIdAndCompletadaTrue(Long usuarioId, Pageable pageable);
   List<Tarea> findAllByUsuarioUsuarioIdAndCompletadaTrue(Long usuarioId);
   Page<Tarea> findAllByUsuarioUsuarioIdAndCompletadaFalseAndCaducadaTrue(Long usuarioId, Pageable pageable);
   List<Tarea> findAllByUsuarioUsuarioIdAndCompletadaFalseAndCaducadaTrue(Long usuarioId);
}
