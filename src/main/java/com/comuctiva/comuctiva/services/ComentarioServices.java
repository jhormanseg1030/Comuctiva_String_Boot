package com.comuctiva.comuctiva.services;

import java.util.List;

import com.comuctiva.comuctiva.Dto.ComentarioCreateDto;
import com.comuctiva.comuctiva.Dto.ComentarioDto;
import com.comuctiva.comuctiva.Dto.ComentarioUpdateDto;

public interface ComentarioServices {
    ComentarioDto crearComentario(ComentarioCreateDto comentarioCreateDto);
    ComentarioDto buscarPorId(Integer id);
    List<ComentarioDto> listarTodos();
    List<ComentarioDto> listarPorProducto(Integer idProducto);
    List<ComentarioDto> listarPorUsuario(Integer idUsuario);
    ComentarioDto actualizarComentario(ComentarioUpdateDto comentarioUpdateDto);
    void eliminarComentario(Integer id);
}
