package com.comuctiva.comuctiva.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comuctiva.comuctiva.models.Producto;

public interface ProductoRepositorie extends JpaRepository<Producto, Integer> {

}
