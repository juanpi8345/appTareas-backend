
package com.api.tareas.servicios;

import com.api.tareas.entidades.Tarea;
import com.api.tareas.repositorios.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TareaServiceImpl implements TareaService {
    
    @Autowired
    private TareaRepository tareaRepository;

    @Override
    public Page<Tarea> obtenerTareasPorUsuarioId(Long usuarioId,Pageable pageable) {
       return tareaRepository.findAllByUsuarioUsuarioId(usuarioId, pageable);
    }

    @Override
    public Tarea obtenerTareaPorId(Long tareaId) {
     return tareaRepository.findById(tareaId).orElse(null);
    }

    @Override
    public Tarea actualizarTarea(Tarea tareaRequest) {
        return tareaRepository.findById(tareaRequest.getTareaId()).map((Tarea tarea) ->{
            tarea.setTitulo(tareaRequest.getTitulo());
            tarea.setCompletada(tareaRequest.isCompletada());
            tarea.setDescripcion(tareaRequest.getDescripcion());
            tarea.setFechaCaducidad(tareaRequest.getFechaCaducidad());
            return tareaRepository.save(tarea);
        }).orElse(null);
    }

    @Override
    public void eliminarTarea(Long tareaId) {
         tareaRepository.deleteById(tareaId);
    }

    @Override
    public Tarea guardarTarea(Tarea tarea) {
        return tareaRepository.save(tarea);
    }
    
}
