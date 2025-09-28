package com.comuctiva.comuctiva.repositoryes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comuctiva.comuctiva.models.Comp_Produc;
import com.comuctiva.comuctiva.models.Comp_ProducId;
import com.comuctiva.comuctiva.models.Compra;

@Repository
public interface Comp_ProductRepositories extends JpaRepository<Comp_Produc,Comp_ProducId> {
    List<Comp_Produc> findByIdCompraId(Integer compraId);
    List<Comp_Produc> findByIdProductoId(Integer productoId);
    List<Comp_Produc> findByCompra(Compra compra);
}
