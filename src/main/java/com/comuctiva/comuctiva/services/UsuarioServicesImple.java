package com.comuctiva.comuctiva.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comuctiva.comuctiva.Dto.UsuarioCreateDto;
import com.comuctiva.comuctiva.Dto.UsuarioDto;
import com.comuctiva.comuctiva.Dto.UsuarioUpdateDto;
import com.comuctiva.comuctiva.Mapper.UsuarioMapper;
import com.comuctiva.comuctiva.models.Tip_Doc;
import com.comuctiva.comuctiva.models.Usuario;
import com.comuctiva.comuctiva.repositoryes.Tip_DocRepositories;
import com.comuctiva.comuctiva.repositoryes.UsuarioRepositories;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioServicesImple implements UsuarioServices {

    private final UsuarioRepositories usuarioRepositories;
    private final UsuarioMapper usuarioMapper;
    private final Tip_DocRepositories tip_DocRepositories;

    public UsuarioServicesImple(UsuarioRepositories usuarioRepositories, UsuarioMapper usuarioMapper, Tip_DocRepositories tip_DocRepositories) {
        this.usuarioRepositories = usuarioRepositories;
        this.usuarioMapper = usuarioMapper;
        this.tip_DocRepositories = tip_DocRepositories;
    }
    @Override
    @Transactional
    public UsuarioDto crearUsuario(UsuarioCreateDto usuarioCreateDto) {
        Usuario usuario = usuarioMapper.toUsuario(usuarioCreateDto);
        Usuario usuarioGuardado = usuarioRepositories.save(usuario);
        return usuarioMapper.toUsuarioDto(usuarioGuardado);
    }
    @Override
    @Transactional(readOnly = true)
    public UsuarioDto buscarPorId(Integer id_usu) {
        Usuario usuario= usuarioRepositories.findById(id_usu)
        .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        return usuarioMapper.toUsuarioDto(usuario);
    }
    @Override
    @Transactional(readOnly = true)
    public List<UsuarioDto> listartodos() {
        return usuarioRepositories.findAll()
                .stream()
                .map(usuarioMapper::toUsuarioDto)
                .collect(Collectors.toList());
    }
    @Override
    public void eliminarUsuario(Integer id_usu) {
        Usuario usuario = usuarioRepositories.findById(id_usu)
        .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        usuarioRepositories.delete(usuario);
    }
    @Override
    @Transactional
    public UsuarioDto actualizarUsuario(UsuarioUpdateDto usuaUpdaDto){
        Usuario usuario = usuarioRepositories.findById(usuaUpdaDto.getId_us())
        .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        usuario.setNom_Usu(usuaUpdaDto.getNomb());
        usuario.setApell1(usuaUpdaDto.getApell());
        usuario.setApell2(usuaUpdaDto.getApell2());
        usuario.setTel(usuaUpdaDto.getTelefo());
        usuario.setTel2(usuaUpdaDto.getTelefo2());
        usuario.setCorreo(usuaUpdaDto.getCorr());
        usuario.setNumDoc(usuaUpdaDto.getNumdocument());
        usuario.setPassword(usuaUpdaDto.getPasswo());

        Tip_Doc tip_Doc = tip_DocRepositories.findById(usuaUpdaDto.getTipDocuId())
        .orElseThrow(() -> new EntityNotFoundException("Tipo de Documento no encontrado"));
        usuario.setTip_Doc(tip_Doc);

        Usuario actualizado = usuarioRepositories.save(usuario);
        return usuarioMapper.toUsuarioDto(actualizado);
    }
}
