package com.comuctiva.comuctiva.Mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.comuctiva.comuctiva.Dto.ComentarioCreateDto;
import com.comuctiva.comuctiva.Dto.ComentarioDto;
import com.comuctiva.comuctiva.models.Comentarios;
import com.comuctiva.comuctiva.models.Comp_Produc;
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

        // Buscar Comp_Produc por ID simple
        Comp_Produc compProduc = compProducRepositories.findById(comentarioCreateDto.getIdCompProduc())
                .orElseThrow(() -> new EntityNotFoundException("Compra-Producto no encontrada con ID: " + comentarioCreateDto.getIdCompProduc()));
        comentario.setCompProduc(compProduc);

        // Poblar id_compra e id_producto desde Comp_Produc
        if (compProduc.getCompra() != null) {
            comentario.setIdCompra(compProduc.getCompra().getId_compra());
        }
        if (compProduc.getProduc() != null) {
            comentario.setIdProducto(compProduc.getProduc().getId_producto());
        }

        // Buscar Usuario
        Usuario usuario = usuarioRepositories.findById(comentarioCreateDto.getIdUsuario())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + comentarioCreateDto.getIdUsuario()));
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
            dto.setIdCompProduc(comentario.getCompProduc().getIdComProduc());
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
