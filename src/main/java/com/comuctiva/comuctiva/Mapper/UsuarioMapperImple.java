package com.comuctiva.comuctiva.Mapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.comuctiva.comuctiva.Dto.UsuarioCreateDto;
import com.comuctiva.comuctiva.Dto.UsuarioDto;
import com.comuctiva.comuctiva.models.Tip_Doc;
import com.comuctiva.comuctiva.models.Usuario;
import com.comuctiva.comuctiva.repositoryes.Tip_DocRepositories;

import jakarta.persistence.EntityNotFoundException;

@Component
public class UsuarioMapperImple implements UsuarioMapper{

    private final Tip_DocRepositories tip_DocRepositories;
    private final PasswordEncoder passwordEncoder;

    public UsuarioMapperImple(Tip_DocRepositories tip_DocRepositories, PasswordEncoder passwordEncoder){
        this.tip_DocRepositories = tip_DocRepositories;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Usuario toUsuario(UsuarioCreateDto usuaCreDto){
        if(usuaCreDto == null)
        return null;

        Usuario usuario = new Usuario();
        usuario.setNom_Usu(usuaCreDto.getNombre());
        usuario.setApell1(usuaCreDto.getApellido());
        usuario.setApell2(usuaCreDto.getApellido2());
        usuario.setTel(usuaCreDto.getTelefono());
        usuario.setTel2(usuaCreDto.getTelefono2());
        usuario.setCorreo(usuaCreDto.getCorreo());
        usuario.setNumDoc(usuaCreDto.getNumdocumento());
        // Encriptar la contraseña antes de guardarla
        usuario.setPassword(passwordEncoder.encode(usuaCreDto.getPassword()));
        
        Tip_Doc tip_Doc = tip_DocRepositories.findById(usuaCreDto.getTipId())
        .orElseThrow(() -> new EntityNotFoundException("Tipo de documento no encontrado"));
        usuario.setTip_Doc(tip_Doc);
        return usuario;
    }
    @Override
    public UsuarioDto toUsuarioDto(Usuario usuario){
        if (usuario == null)
        return null;

        return new UsuarioDto(
            usuario.getId_Usuario(),
            usuario.getNom_Usu(),
            usuario.getApell1(),
            usuario.getApell2(),
            usuario.getTel(),
            usuario.getTel2(),
            usuario.getCorreo(),
            usuario.getNumDoc(),
            usuario.getPassword(),
            usuario.getTip_Doc() != null ? usuario.getTip_Doc().getId_tipdocu() : null,
            usuario.getTip_Doc() != null ? usuario.getTip_Doc().getTipo() : null);
    }
}
