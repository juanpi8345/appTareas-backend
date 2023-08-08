package com.api.tareas.controladores;

import com.api.tareas.entidades.Tarea;
import com.api.tareas.entidades.Usuario;
import com.api.tareas.servicios.TareaService;
import com.api.tareas.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tareas")
@CrossOrigin("*")
public class TareaController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private TareaService tareaService;
    
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<Page<Tarea>> obtenerTareasPorUsuario(@PathVariable Long usuarioId, 
                                                                                                    @RequestParam(defaultValue = "0") int page,
                                                                                                    @RequestParam(defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size);
        return  ResponseEntity.ok(tareaService.obtenerTareasPorUsuarioId(usuarioId, pageable));
    }
    
    @PostMapping("/usuario/{usuarioId}")
    public ResponseEntity<Tarea> agregarTarea(@PathVariable Long usuarioId, @RequestBody Tarea tarea){
        Usuario usuario  = usuarioService.obtenerUsuarioPorId(usuarioId);
        if(usuario!= null){
            usuario.getTareas().add(tarea);
            tarea.setUsuario(usuario);
            tareaService.guardarTarea(tarea);
            return ResponseEntity.ok(tarea);
        }
        return ResponseEntity.notFound().build();
    }
    
    @PutMapping("/")
    public ResponseEntity<Tarea> actualizarTarea(@RequestBody Tarea tarea){
       Tarea tareaEditar = tareaService.actualizarTarea(tarea);
       if(tareaEditar != null){
           return ResponseEntity.ok(tareaEditar);
       }
       return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/tarea/{tareaId}")
    public ResponseEntity<?> eliminarTarea(@PathVariable Long tareaId){ 
         tareaService.eliminarTarea(tareaId);
         return ResponseEntity.noContent().build();   
    }
    
 
    
   
            
}
