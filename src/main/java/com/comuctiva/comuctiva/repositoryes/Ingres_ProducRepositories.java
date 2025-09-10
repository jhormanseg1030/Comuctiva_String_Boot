package com.comuctiva.comuctiva.repositoryes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comuctiva.comuctiva.models.Ingres_Produc;
import com.comuctiva.comuctiva.models.Ingres_ProducId;

@Repository
public interface Ingres_ProducRepositories extends JpaRepository <Ingres_Produc,Ingres_ProducId>{
List <Ingres_Produc> findByIdProductoId(Integer productoId);
List <Ingres_Produc> findByIdIngresoId(Integer ingresoId);

}
