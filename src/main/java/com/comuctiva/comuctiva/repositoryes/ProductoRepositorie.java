package com.comuctiva.comuctiva.repositoryes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.comuctiva.comuctiva.models.Producto;

public interface ProductoRepositorie extends JpaRepository<Producto, Integer> {
	// Buscar productos por estado
	java.util.List<Producto> findByEstado(String estado);
}
