package com.comuctiva.comuctiva.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comuctiva.comuctiva.models.Usuario;

public interface UsuarioRepositories extends JpaRepository<Usuario, Integer> {

}
