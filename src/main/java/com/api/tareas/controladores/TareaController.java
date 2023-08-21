package com.api.tareas.controladores;

import com.api.tareas.entidades.Tarea;
import com.api.tareas.entidades.Usuario;
import com.api.tareas.servicios.TareaService;
import com.api.tareas.servicios.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

@CrossOrigin("*")
@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TareaService tareaService;

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<Page<Tarea>> obtenerTareasPendientesPorUsuario(@PathVariable Long usuarioId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "fechaCaducidad") String orderBy) {
        Sort sort = Sort.by(orderBy);

        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(tareaService.obtenerTareasPendientesPorUsuarioId(usuarioId, pageable));
    }

    @GetMapping("/completadas/usuario/{usuarioId}")
    public ResponseEntity<Page<Tarea>> obtenerTareasCompletadasPorUsuario(@PathVariable Long usuarioId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "fechaCaducidad") String orderBy) {
        Sort sort = Sort.by(orderBy);

        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(tareaService.obtenerTareasCompletadasPorUsuarioId(usuarioId, pageable));
    }

    @GetMapping("/caducadas/usuario/{usuarioId}")
    public ResponseEntity<Page<Tarea>> obtenerTareasCaducadasPorUsuario(@PathVariable Long usuarioId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "fechaCaducidad") String orderBy) {
        Sort sort = Sort.by(orderBy);

        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(tareaService.obtenerTareasCaducadasPorUsuarioId(usuarioId, pageable));
    }

    @PostMapping("/usuario/{usuarioId}")
    public ResponseEntity<Tarea> agregarTarea(@PathVariable Long usuarioId, @RequestBody Tarea tarea) {
        Usuario usuario = usuarioService.obtenerUsuarioPorId(usuarioId);
        if (usuario != null) {
            usuario.getTareas().add(tarea);
            tarea.setUsuario(usuario);
            tareaService.guardarTarea(tarea);
            System.out.println(tarea.getFechaCaducidad());
            return ResponseEntity.ok(tarea);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/")
    public ResponseEntity<Tarea> marcarCompletada(@RequestBody Tarea tarea) {
        tareaService.actualizarTarea(tarea);
        return ResponseEntity.ok(tarea);
    }

    @PutMapping("/usuario/{usuarioId}/completar")
    public ResponseEntity<List<Tarea>> marcarTodasComoCompletadas(@PathVariable Long usuarioId) {
        List<Tarea> tareas = tareaService.obtenerTareasPendientesPorUsuarioId(usuarioId);
        for (Tarea tarea : tareas) {
           tareaService.actualizarTarea(tarea);
        }
        return ResponseEntity.ok(tareas);
    }

    @DeleteMapping("/tarea/{tareaId}")
    public ResponseEntity<?> eliminarTarea(@PathVariable Long tareaId) {
        tareaService.eliminarTarea(tareaId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> eliminarTareasCompletadas(@PathVariable Long usuarioId) {
        tareaService.eliminarTareas(tareaService.obtenerTareasCompletadasPorUsuarioId(usuarioId));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/caducadas/usuario/{usuarioId}")
    public ResponseEntity<?> eliminarTareasCaducadas(@PathVariable Long usuarioId) {
        tareaService.eliminarTareas(tareaService.obtenerTareasCaducadasPorUsuarioId(usuarioId));
        return ResponseEntity.noContent().build();
    }

}
