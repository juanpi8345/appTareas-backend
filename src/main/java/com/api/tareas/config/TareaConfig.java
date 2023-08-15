
package com.api.tareas.config;

import com.api.tareas.servicios.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class TareaConfig {
    
    @Autowired
    private TareaService tareaService;
    
   @Scheduled(fixedRate = 15000)
    public void verificarFechasCaducadas() {
        tareaService.marcarComoCaducadaSiEsNecesario();
    }
    
    
}
