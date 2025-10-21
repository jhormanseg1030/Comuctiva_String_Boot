package com.comuctiva.comuctiva.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comuctiva.comuctiva.Dto.UsuarioCreateDto;
import com.comuctiva.comuctiva.Dto.UsuarioDto;
import com.comuctiva.comuctiva.Dto.UsuarioUpdateDto;
import com.comuctiva.comuctiva.services.UsuarioServices;
import com.comuctiva.comuctiva.Dto.LoginRequest;
import com.comuctiva.comuctiva.Dto.RespuestaLoginDto;
import com.comuctiva.comuctiva.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/usuario")
public class UsuarioController {
    private final UsuarioServices usuarioServices;
    @Autowired
    private JwtUtil jwtUtil;

    public UsuarioController(UsuarioServices usuarioServices) {
        this.usuarioServices = usuarioServices;
    }
    // Endpoint de login que retorna el token JWT
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("=== INICIO LOGIN ===");
        System.out.println("TipDocId: " + loginRequest.getTipDocId());
        System.out.println("NumDoc: " + loginRequest.getNumDoc());
        System.out.println("Password: " + loginRequest.getPassword());
        
        var usuario = usuarioServices.buscarPorLogin(
            loginRequest.getTipDocId(),
            loginRequest.getNumDoc(),
            loginRequest.getPassword()
        );
        
        System.out.println("Usuario encontrado: " + (usuario != null ? usuario.getNom_Usu() : "null"));
        
        if (usuario != null) {
            String token = jwtUtil.generateToken(usuario.getNumDoc().toString());
            System.out.println("Token generado: " + token);
            
            // Crear el DTO de respuesta evitando problemas de serialización con Hibernate
            RespuestaLoginDto respuesta = new RespuestaLoginDto(
                token,
                usuario.getId_Usuario(),
                usuario.getNom_Usu(),
                usuario.getApell1(),
                usuario.getApell2(),
                usuario.getTel(),
                usuario.getTel2(),
                usuario.getCorreo(),
                usuario.getNumDoc(),
                usuario.getTip_Doc().getId_tipdocu(),
                usuario.getTip_Doc().getTipo()
            );
            
            System.out.println("=== FIN LOGIN EXITOSO ===");
            return ResponseEntity.ok(respuesta);
        }
        System.out.println("=== FIN LOGIN FALLIDO ===");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
    }
    
    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody UsuarioCreateDto usuarioCreate) {
        try{
            UsuarioDto usuario = usuarioServices.crearUsuario(usuarioCreate);
            return ResponseEntity.status(HttpStatus.CREATED)
            .body(Map.of("Mensaje", "Usuario creado exitosamente", "Detalles", usuario));
        }catch (IllegalStateException ex){
            return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(Map.of("Mensaje", ex.getMessage()));
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(Map.of("Error", "Error al crear un usuario, por favor vuelva a intentar", "Detalles", ex.getMessage()));
        }
    }

    @GetMapping("/{id_Usuario}")
    public ResponseEntity<UsuarioDto> obtenerId(@PathVariable Integer id_Usuario) {
        UsuarioDto usuario = usuarioServices.buscarPorId(id_Usuario);
        return ResponseEntity.ok(usuario);
    }
    @GetMapping
    public ResponseEntity<List<UsuarioDto>> listar() {
        List<UsuarioDto> usuario = usuarioServices.listartodos();
        return ResponseEntity.ok(usuario);
    }
    @PutMapping("/{id_Usuario}")
    public ResponseEntity<UsuarioDto> putActu(@PathVariable Integer id_Usuario, @RequestBody UsuarioUpdateDto uptadeUsu) {
        uptadeUsu.setId_us(id_Usuario);
        UsuarioDto actualizar = usuarioServices.actualizarUsuario(uptadeUsu);
        return ResponseEntity.ok(actualizar);
    }

    @DeleteMapping("/{id_Usuario}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Integer id_Usuario) {
        usuarioServices.eliminarUsuario(id_Usuario);
        return ResponseEntity.ok().build();
    }
}
