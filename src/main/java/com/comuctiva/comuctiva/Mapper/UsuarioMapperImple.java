package com.comuctiva.comuctiva.Mapper;

import org.springframework.stereotype.Component;

import com.comuctiva.comuctiva.Dto.UsuarioDto;
import com.comuctiva.comuctiva.models.Tip_Doc;
import com.comuctiva.comuctiva.models.Usuario;
import com.comuctiva.comuctiva.repositoryes.Tip_DocRepositories;

import jakarta.persistence.EntityNotFoundException;

@Component
public class UsuarioMapperImple implements UsuarioMapper{

    private final Tip_DocRepositories tip_DocRepositories;

    public UsuarioMapperImple(Tip_DocRepositories tip_DocRepositories){
        this.tip_DocRepositories=tip_DocRepositories;
    }

    @Override
    public Usuario toUsuario(UsuarioDto usuarioDto){
        Usuario usuario = new Usuario();
        usuario.setId_Usuario(usuarioDto.getId_usu());
        usuario.setNom_Usu(usuarioDto.getNom_u());
        usuario.setApell1(usuarioDto.getApe());
        usuario.setApell2(usuarioDto.getApe2());
        usuario.setTel(usuarioDto.getTele());
        usuario.setTel2(usuarioDto.getTele2());
        usuario.setCorreo(usuarioDto.getCorr());
        usuario.setNumDoc(usuarioDto.getNumdocu());
        usuario.setPassword(usuarioDto.getPassw());
        
        Tip_Doc tip_Doc = tip_DocRepositories.findById(usuarioDto.getTipdocuId())
        .orElseThrow(() -> new EntityNotFoundException("Tipo de documento no encontrado"));
        usuario.setTip_Doc(tip_Doc);
        return usuario;
    }
    @Override
    public UsuarioDto toUsuarioDto(Usuario usuario){
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
            usuario.getTip_Doc().getId_tipdocu(),
            usuario.getTip_Doc().getTipo());
    }
}
