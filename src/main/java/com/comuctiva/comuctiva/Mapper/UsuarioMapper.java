package com.comuctiva.comuctiva.Mapper;

import com.comuctiva.comuctiva.Dto.UsuarioCreateDto;
import com.comuctiva.comuctiva.Dto.UsuarioDto;
import com.comuctiva.comuctiva.models.Usuario;

public interface UsuarioMapper {

    Usuario toUsuario(UsuarioCreateDto usuaCreDto);

    UsuarioDto toUsuarioDto(Usuario usuario);
}
