package com.comuctiva.comuctiva.Mapper;

import com.comuctiva.comuctiva.Dto.ComentarioCreateDto;
import com.comuctiva.comuctiva.Dto.ComentarioDto;
import com.comuctiva.comuctiva.models.Comentarios;

public interface ComentarioMapper {
    Comentarios toComentario(ComentarioCreateDto comentarioCreateDto);
    ComentarioDto toComentarioDto(Comentarios comentario);
}
