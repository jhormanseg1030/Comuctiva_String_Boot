package com.comuctiva.comuctiva.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comuctiva.comuctiva.Dto.ComentarioCreateDto;
import com.comuctiva.comuctiva.Dto.ComentarioDto;
import com.comuctiva.comuctiva.Dto.ComentarioUpdateDto;
import com.comuctiva.comuctiva.Mapper.ComentarioMapper;
import com.comuctiva.comuctiva.models.Comentarios;
import com.comuctiva.comuctiva.repositoryes.ComentariosRepositories;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ComentarioServicesImple implements ComentarioServices {

    private final ComentariosRepositories comentariosRepositories;
    private final ComentarioMapper comentarioMapper;

    public ComentarioServicesImple(ComentariosRepositories comentariosRepositories,
                                   ComentarioMapper comentarioMapper) {
        this.comentariosRepositories = comentariosRepositories;
        this.comentarioMapper = comentarioMapper;
    }

    @Override
    @Transactional
    public ComentarioDto crearComentario(ComentarioCreateDto comentarioCreateDto) {
        // Validar campos obligatorios
        if (comentarioCreateDto.getComentario() == null || comentarioCreateDto.getComentario().trim().isEmpty()) {
            throw new IllegalStateException("El comentario no puede estar vacío");
        }
        if (comentarioCreateDto.getIdCompProduc() == null) {
            throw new IllegalStateException("El ID de compra-producto es requerido");
        }
        if (comentarioCreateDto.getIdUsuario() == null) {
            throw new IllegalStateException("El ID de usuario es requerido");
        }

        Comentarios comentario = comentarioMapper.toComentario(comentarioCreateDto);
        Comentarios comentarioGuardado = comentariosRepositories.save(comentario);
        return comentarioMapper.toComentarioDto(comentarioGuardado);
    }

    @Override
    @Transactional(readOnly = true)
    public ComentarioDto buscarPorId(Integer id) {
        Comentarios comentario = comentariosRepositories.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comentario no encontrado"));
        return comentarioMapper.toComentarioDto(comentario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ComentarioDto> listarTodos() {
        return comentariosRepositories.findAll()
                .stream()
                .map(comentarioMapper::toComentarioDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ComentarioDto> listarPorProducto(Integer idProducto) {
        return comentariosRepositories.findByProductoId(idProducto)
                .stream()
                .map(comentarioMapper::toComentarioDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ComentarioDto> listarPorUsuario(Integer idUsuario) {
        return comentariosRepositories.findByUsuarioId(idUsuario)
                .stream()
                .map(comentarioMapper::toComentarioDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ComentarioDto actualizarComentario(ComentarioUpdateDto comentarioUpdateDto) {
        Comentarios comentario = comentariosRepositories.findById(comentarioUpdateDto.getIdComentario())
                .orElseThrow(() -> new EntityNotFoundException("Comentario no encontrado"));

        if (comentarioUpdateDto.getComentario() != null && !comentarioUpdateDto.getComentario().trim().isEmpty()) {
            comentario.setComentario(comentarioUpdateDto.getComentario());
        }

        Comentarios actualizado = comentariosRepositories.save(comentario);
        return comentarioMapper.toComentarioDto(actualizado);
    }

    @Override
    @Transactional
    public void eliminarComentario(Integer id) {
        Comentarios comentario = comentariosRepositories.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comentario no encontrado"));
        comentariosRepositories.delete(comentario);
    }
}
