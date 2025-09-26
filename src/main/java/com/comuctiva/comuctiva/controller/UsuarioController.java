package com.comuctiva.comuctiva.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/usuario")
public class UsuarioController {
    private final UsuarioServices usuarioServices;
    public UsuarioController(UsuarioServices usuarioServices) {
        this.usuarioServices = usuarioServices;
    }
    
    @PostMapping("/crear")
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody UsuarioCreateDto usuarioCreate) {
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
}
