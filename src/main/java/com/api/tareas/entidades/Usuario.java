
package com.api.tareas.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "usuarios")
public class Usuario {

    public Usuario() {
    }

    public Usuario(Long usuarioId, String nombre, String apellido, String username, String password) {
        this.usuarioId = usuarioId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.username = username;
        this.password = password;
    }
    
    @Id
    @Column(name = "usuario_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioId;
    
    @Column(nullable  = false)
    private String nombre;
    
   @Column(nullable  =false)
    private String apellido;
   
   @Column(unique = true, nullable  =false)
    private String username;
   
   @Column(nullable  =false)
    private String password;
   
   @OneToMany(mappedBy = "usuario",cascade = CascadeType.ALL)
   @JsonIgnore
   private Set<Tarea> tareas = new HashSet<>();
   
   
   
}
