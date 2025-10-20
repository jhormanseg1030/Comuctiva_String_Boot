package com.comuctiva.comuctiva.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comuctiva.comuctiva.models.Pedidos;

import java.util.List;
public interface PedidoRepositorie extends JpaRepository <Pedidos, Integer>{
	List<Pedidos> findByUsuario_NumDoc(Long numDoc);
}
