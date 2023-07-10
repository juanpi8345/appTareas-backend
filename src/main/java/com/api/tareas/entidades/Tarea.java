
package com.api.tareas.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Getter @Setter
@Table(name = "tareas")
public class Tarea {

    public Tarea() {
    }

    public Tarea(Long tareaId, String titulo, String descripcion, Date fechaCaducidad) {
        this.tareaId = tareaId;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaCaducidad = fechaCaducidad;
    }

    @Id
    @Column(name = "tarea_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tareaId;
    
    @Column(unique = true, nullable = false)
    private String titulo;
   
    @Lob
    @Column(unique = true, nullable = false)
    private String descripcion;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_caducidad")
    private Date fechaCaducidad;
    
    @Column(nullable = false)
    private boolean completada = false;
    
    @ManyToOne()
    @JoinColumn(name="usuario_id")
    @JsonIgnore
    private Usuario usuario;

}
