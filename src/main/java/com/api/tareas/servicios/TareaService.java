package com.api.tareas.servicios;

import com.api.tareas.entidades.Tarea;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TareaService {
    
    Page<Tarea> obtenerTareasPendientesPorUsuarioId(Long usuarioId, Pageable pageable);
    Page<Tarea> obtenerTareasCompletadasPorUsuarioId(Long usuarioId, Pageable pageable);
    Tarea obtenerTareaPorId(Long tareaId);
    Tarea actualizarTarea(Tarea tarea);
    Tarea guardarTarea(Tarea tarea);
    void eliminarTarea(Long tareaId);
    
}
