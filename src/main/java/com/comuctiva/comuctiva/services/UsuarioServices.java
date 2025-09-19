package com.comuctiva.comuctiva.services;

import java.util.List;

import com.comuctiva.comuctiva.Dto.UsuarioCreateDto;
import com.comuctiva.comuctiva.Dto.UsuarioDto;
import com.comuctiva.comuctiva.Dto.UsuarioUpdateDto;

public interface UsuarioServices {
    // Define los métodos del servicio aquí, por ejemplo:
    UsuarioDto crearUsuario(UsuarioCreateDto usuarioCreateDto);
    
    UsuarioDto buscarPorId(Integer id);

    List<UsuarioDto> listartodos();

    void eliminarUsuario(Integer id);

    UsuarioDto actualizarUsuario(UsuarioUpdateDto usuarioUpdateDto);
}
