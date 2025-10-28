package com.comuctiva.comuctiva.Mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.comuctiva.comuctiva.Dto.ComentarioCreateDto;
import com.comuctiva.comuctiva.Dto.ComentarioDto;
import com.comuctiva.comuctiva.models.Comentarios;
import com.comuctiva.comuctiva.models.Comp_Produc;
import com.comuctiva.comuctiva.models.Comp_ProducId;
import com.comuctiva.comuctiva.models.Usuario;
import com.comuctiva.comuctiva.repositoryes.Comp_ProducRepositories;
import com.comuctiva.comuctiva.repositoryes.UsuarioRepositories;

import jakarta.persistence.EntityNotFoundException;

@Component
public class ComentarioMapperImple implements ComentarioMapper {

    private final Comp_ProducRepositories compProducRepositories;
    private final UsuarioRepositories usuarioRepositories;

    public ComentarioMapperImple(Comp_ProducRepositories compProducRepositories, 
                                 UsuarioRepositories usuarioRepositories) {
        this.compProducRepositories = compProducRepositories;
        this.usuarioRepositories = usuarioRepositories;
    }

    @Override
    public Comentarios toComentario(ComentarioCreateDto comentarioCreateDto) {
        if (comentarioCreateDto == null) {
            return null;
        }

        Comentarios comentario = new Comentarios();
        comentario.setComentario(comentarioCreateDto.getComentario());
        comentario.setFechaComentario(LocalDateTime.now());

        // Buscar Comp_Produc por ID compuesto
        Comp_ProducId compProducId = new Comp_ProducId();
        compProducId.setCompraId(comentarioCreateDto.getIdCompProduc());
        compProducId.setProductoId(comentarioCreateDto.getIdCompProduc());
        
        Comp_Produc compProduc = compProducRepositories.findById(Long.valueOf(comentarioCreateDto.getIdCompProduc()))
                .orElseThrow(() -> new EntityNotFoundException("Compra-Producto no encontrada"));
        comentario.setCompProduc(compProduc);

        // Buscar Usuario
        Usuario usuario = usuarioRepositories.findById(comentarioCreateDto.getIdUsuario())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        comentario.setUsuario(usuario);

        return comentario;
    }

    @Override
    public ComentarioDto toComentarioDto(Comentarios comentario) {
        if (comentario == null) {
            return null;
        }

        ComentarioDto dto = new ComentarioDto();
        dto.setIdComentario(comentario.getIdComentario());
        dto.setComentario(comentario.getComentario());
        dto.setFechaComentario(comentario.getFechaComentario());
        
        if (comentario.getCompProduc() != null) {
            // El ID es compuesto, usamos el ID de compra como referencia
            dto.setIdCompProduc(comentario.getCompProduc().getId().getCompraId());
            if (comentario.getCompProduc().getProduc() != null) {
                dto.setNombreProducto(comentario.getCompProduc().getProduc().getNomprod());
            }
        }
        
        if (comentario.getUsuario() != null) {
            dto.setIdUsuario(comentario.getUsuario().getId_Usuario());
            dto.setNombreUsuario(comentario.getUsuario().getNom_Usu());
        }

        return dto;
    }
}
