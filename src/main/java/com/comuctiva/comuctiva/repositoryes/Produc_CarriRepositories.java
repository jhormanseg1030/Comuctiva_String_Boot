package com.comuctiva.comuctiva.repositoryes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comuctiva.comuctiva.models.Produc_Carri;
import com.comuctiva.comuctiva.models.Produc_CarriId;

@Repository
public interface Produc_CarriRepositories extends JpaRepository<Produc_Carri,Produc_CarriId>{
    List<Produc_Carri> findByIdProdId(Integer prodId);
    List<Produc_Carri> findByIdCarritoId(Integer carritoId);
}
