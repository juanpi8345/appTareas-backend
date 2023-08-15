package com.api.tareas.servicios;

import com.api.tareas.entidades.Tarea;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TareaService {
    
    public void marcarComoCaducadaSiEsNecesario(); 
    Page<Tarea> obtenerTareasPendientesPorUsuarioId(Long usuarioId, Pageable pageable);
    List<Tarea> obtenerTareasPendientesPorUsuarioId(Long usuarioId);
    Page<Tarea> obtenerTareasCompletadasPorUsuarioId(Long usuarioId, Pageable pageable);
    List<Tarea> obtenerTareasCompletadasPorUsuarioId(Long usuarioId);
    Page<Tarea> obtenerTareasCaducadasPorUsuarioId(Long usuarioId, Pageable pageable);
    List<Tarea> obtenerTareasCaducadasPorUsuarioId(Long usuarioId);
    Tarea obtenerTareaPorId(Long tareaId);
    Tarea actualizarTarea(Tarea tarea);
    Tarea guardarTarea(Tarea tarea);
    void eliminarTarea(Long tareaId);
    void eliminarTareas(List<Tarea> tareas);
    
}
