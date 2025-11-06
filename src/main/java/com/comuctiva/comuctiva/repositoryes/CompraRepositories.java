package com.comuctiva.comuctiva.repositoryes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.comuctiva.comuctiva.models.Compra;

public interface CompraRepositories extends JpaRepository<Compra, Integer>{
    // 🆕 Encontrar compras por usuario (a través de pedido)
    @Query("SELECT c FROM Compra c WHERE c.pedido.usuario.id_Usuario = :id_usuario")
    List<Compra> findComprasByUsuario(@Param("id_usuario") Integer id_usuario);
}
