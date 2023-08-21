package com.api.tareas.auth;

import com.api.tareas.entidades.Usuario;
import com.api.tareas.repositorios.UsuarioRepository;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
 
    private final AuthService authService;
     private final UsuarioRepository usuarioRepository;
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }
    
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }
    
    @GetMapping("/actual-usuario")
    public Usuario obtenerUsuarioActual(Principal principal){
        UserDetails user=usuarioRepository.findByUsername(principal.getName()).orElseThrow();
        return (Usuario) user;
    }
}
