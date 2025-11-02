package com.comuctiva.comuctiva.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    public UsuarioServicesImple(UsuarioRepositories usuarioRepositories, UsuarioMapper usuarioMapper, Tip_DocRepositories tip_DocRepositories, PasswordEncoder passwordEncoder) {
        this.usuarioRepositories = usuarioRepositories;
        this.usuarioMapper = usuarioMapper;
        this.tip_DocRepositories = tip_DocRepositories;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Usuario buscarPorDocumento(String documento) {
        try {
            Long numDoc = Long.parseLong(documento);
            return usuarioRepositories.findByNumDoc(numDoc);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario buscarPorLogin(Integer tipDocId, Long numDoc, String password) {
        System.out.println("=== BUSCANDO USUARIO ===");
        System.out.println("TipDocId recibido: " + tipDocId + " (tipo: " + tipDocId.getClass().getName() + ")");
        System.out.println("NumDoc recibido: " + numDoc + " (tipo: " + numDoc.getClass().getName() + ")");
        
        // Buscar usuario por tipDocId y numDoc
        var usuarioOpt = usuarioRepositories.findFirstByTipDocAndNumDoc(tipDocId, numDoc);
        
        if (usuarioOpt.isEmpty()) {
            System.out.println("❌ Usuario NO encontrado en BD");
            return null;
        }
        
        Usuario usuario = usuarioOpt.get();
        System.out.println("Usuario encontrado en BD: " + usuario.getNom_Usu());
        
        // Verificar contraseña
        String storedPassword = usuario.getPassword();
        System.out.println("Stored hash (prefix): " + (storedPassword != null && storedPassword.length() > 6 ? storedPassword.substring(0, 6) + "..." : "null"));
        
        if (storedPassword == null) {
            System.out.println("⚠️ Contraseña almacenada es null");
            return null;
        }
        
        boolean passwordMatches = passwordEncoder.matches(password, storedPassword);
        System.out.println("Password matches: " + passwordMatches);
        
        if (!passwordMatches) {
            System.out.println("❌ Contraseña incorrecta");
            return null;
        }
        
        System.out.println("✅ Login exitoso");
        System.out.println("=== FIN BUSQUEDA ===");
        return usuario;
    }
    
    @Override
    @Transactional
    public UsuarioDto crearUsuario(UsuarioCreateDto usuarioCreateDto) {
        // Validar campos obligatorios
        if (usuarioCreateDto.getNombre() == null || usuarioCreateDto.getNombre().trim().isEmpty()) {
            throw new IllegalStateException("Falta el campo: nombre");
        }
        if (usuarioCreateDto.getApellido() == null || usuarioCreateDto.getApellido().trim().isEmpty()) {
            throw new IllegalStateException("Falta el campo: apellido");
        }
        if (usuarioCreateDto.getCorreo() == null || usuarioCreateDto.getCorreo().trim().isEmpty()) {
            throw new IllegalStateException("Falta el campo: correo");
        }
        if (usuarioCreateDto.getNumdocumento() == null) {
            throw new IllegalStateException("Falta el campo: numdocumento");
        }
        if (usuarioCreateDto.getPassword() == null || usuarioCreateDto.getPassword().trim().isEmpty()) {
            throw new IllegalStateException("Falta el campo: password");
        }
        if (usuarioCreateDto.getTipId() == null) {
            throw new IllegalStateException("Falta el campo: tipId");
        }
        // Verificar si ya existe un usuario con el mismo número de documento
        Usuario usuarioExistente = usuarioRepositories.findAll().stream()
                .filter(u -> u.getNumDoc() != null && u.getNumDoc().equals(usuarioCreateDto.getNumdocumento()))
                .findFirst()
                .orElse(null);

        if (usuarioExistente != null) {
            throw new IllegalStateException("Ya existe un usuario con este número de documento");
        }

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
        
        // Encriptar la contraseña si se está actualizando
        if (usuaUpdaDto.getPasswo() != null && !usuaUpdaDto.getPasswo().trim().isEmpty()) {
            usuario.setPassword(passwordEncoder.encode(usuaUpdaDto.getPasswo()));
        }

        Tip_Doc tip_Doc = tip_DocRepositories.findById(usuaUpdaDto.getTipDocuId())
        .orElseThrow(() -> new EntityNotFoundException("Tipo de Documento no encontrado"));
        usuario.setTip_Doc(tip_Doc);

        Usuario actualizado = usuarioRepositories.save(usuario);
        return usuarioMapper.toUsuarioDto(actualizado);
    }
}
