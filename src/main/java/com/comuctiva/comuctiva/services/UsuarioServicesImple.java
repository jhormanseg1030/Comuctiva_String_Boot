package com.comuctiva.comuctiva.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.comuctiva.comuctiva.Dto.UsuarioDto;
import com.comuctiva.comuctiva.Mapper.UsuarioMapper;
import com.comuctiva.comuctiva.models.Usuario;
import com.comuctiva.comuctiva.repositoryes.UsuarioRepositories;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioServicesImple implements UsuarioServices {

    private final UsuarioRepositories usuarioRepositories;
    private final UsuarioMapper usuarioMapper;

    public UsuarioServicesImple(UsuarioRepositories usuarioRepositories, UsuarioMapper usuarioMapper) {
        this.usuarioRepositories = usuarioRepositories;
        this.usuarioMapper = usuarioMapper;
    }
    @Override
    public UsuarioDto crearUsuario(UsuarioDto usuarioDto) {
        Usuario usuario = usuarioMapper.toUsuario(usuarioDto);
        Usuario usuarioGuardado = usuarioRepositories.save(usuario);
        return usuarioMapper.toUsuarioDto(usuarioGuardado);
    }
    @Override
    public UsuarioDto usuarioPorId(Integer id) {
        return usuarioRepositories.findById(id)
                .map(usuarioMapper::toUsuarioDto)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con id: "));
    }
    @Override
    public List<UsuarioDto> listartodos() {
        return usuarioRepositories.findAll()
                .stream()
                .map(usuarioMapper::toUsuarioDto)
                .collect(Collectors.toList());
    }
    @Override
    public void eliminarUsuario(Integer id) {
        usuarioRepositories.deleteById(id);
    }
}
