package com.comuctiva.comuctiva.repositoryes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comuctiva.comuctiva.models.Rol_Usuario;
import com.comuctiva.comuctiva.models.Rol_UsuarioId;

@Repository
public interface Rol_UsuarioRepositories extends JpaRepository <Rol_Usuario,Rol_UsuarioId>{
    List<Rol_Usuario> findByIdRolId(Integer rolId);
    List<Rol_Usuario> findByIdUsuarioId(Integer usuarioId);
}
