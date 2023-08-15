
package com.api.tareas.servicios;

import com.api.tareas.entidades.Tarea;
import com.api.tareas.repositorios.TareaRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TareaServiceImpl implements TareaService {
    
    @Autowired
    private TareaRepository tareaRepository;

    @Override
    public Page<Tarea> obtenerTareasPendientesPorUsuarioId(Long usuarioId,Pageable pageable) {
       return tareaRepository.findAllByUsuarioUsuarioIdAndCompletadaFalseAndCaducadaFalse(usuarioId, pageable);
    }

    @Override
    public Tarea obtenerTareaPorId(Long tareaId) {
     return tareaRepository.findById(tareaId).orElse(null);
    }

    @Override
    public Tarea actualizarTarea(Tarea tareaRequest) {
        return tareaRepository.findById(tareaRequest.getTareaId()).map((Tarea tarea) ->{
            tarea.setTitulo(tareaRequest.getTitulo());
            tarea.setCompletada(true);
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

    @Override
    public Page<Tarea> obtenerTareasCompletadasPorUsuarioId(Long usuarioId, Pageable pageable) {
        return tareaRepository.findAllByUsuarioUsuarioIdAndCompletadaTrue(usuarioId, pageable);
    }

    @Override
    public void marcarComoCaducadaSiEsNecesario() {
        List<Tarea> tareas = tareaRepository.findAll();
        
       LocalDate currentDate = LocalDate.now();
        for(Tarea tarea : tareas){
            if(!tarea.isCaducada() && tarea.getFechaCaducidad().isBefore(currentDate)){
                tarea.setCaducada(true);
                tareaRepository.save(tarea);
            }
        }
    }

    @Override
    public Page<Tarea> obtenerTareasCaducadasPorUsuarioId(Long usuarioId, Pageable pageable) {
      return tareaRepository.findAllByUsuarioUsuarioIdAndCompletadaFalseAndCaducadaTrue(usuarioId, pageable);
    }

    @Override
    public List<Tarea> obtenerTareasCompletadasPorUsuarioId(Long usuarioId) {
         return tareaRepository.findAllByUsuarioUsuarioIdAndCompletadaTrue(usuarioId);
    }

    @Override
    public void eliminarTareas(List<Tarea> tareas) {
        tareaRepository.deleteAll(tareas);
    }

    @Override
    public List<Tarea> obtenerTareasCaducadasPorUsuarioId(Long usuarioId) {
       return tareaRepository.findAllByUsuarioUsuarioIdAndCompletadaFalseAndCaducadaTrue(usuarioId);
    }

    @Override
    public List<Tarea> obtenerTareasPendientesPorUsuarioId(Long usuarioId) {
       return tareaRepository.findAllByUsuarioUsuarioIdAndCompletadaFalseAndCaducadaFalse(usuarioId);
    }
    
}
