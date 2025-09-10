package com.comuctiva.comuctiva.repositoryes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comuctiva.comuctiva.models.Rol_Usuario;
import com.comuctiva.comuctiva.models.Rol_UsuarioId;



public interface Rol_UsuarioRepositories extends JpaRepository <Rol_Usuario,Rol_UsuarioId>{
List <Rol_Usuario> finByRol_Id(Long rol_id);
List <Rol_Usuario> findByUsuario_Id(Long usuario_id);
}
