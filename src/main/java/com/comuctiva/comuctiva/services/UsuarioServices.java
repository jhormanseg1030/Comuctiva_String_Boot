package com.comuctiva.comuctiva.services;

import java.util.List;

import com.comuctiva.comuctiva.Dto.UsuarioDto;

public interface UsuarioServices {
    // Define los métodos del servicio aquí, por ejemplo:
    UsuarioDto crearUsuario(UsuarioDto usuarioDto);
    
    UsuarioDto usuarioPorId(Integer id);

    List<UsuarioDto> listartodos();

    void eliminarUsuario(Integer id);

}
