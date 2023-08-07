package com.api.tareas;

import com.api.tareas.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TareasApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TareasApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
      
    }

}
