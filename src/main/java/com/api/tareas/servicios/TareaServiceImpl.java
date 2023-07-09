
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
       return tareaRepository.findAllByUsuarioId(usuarioId, pageable);
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Tarea guardarTarea(Tarea tarea) {
        if(tareaRepository.findById(tarea.getTareaId()) == null)
             return tareaRepository.save(tarea);
    
       return null;
    }
    
}
