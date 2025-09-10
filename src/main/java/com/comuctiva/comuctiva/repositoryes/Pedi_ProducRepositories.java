package com.comuctiva.comuctiva.repositoryes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comuctiva.comuctiva.models.Pedi_Produc;
import com.comuctiva.comuctiva.models.Pedi_producId;

@Repository
public interface Pedi_ProducRepositories extends JpaRepository <Pedi_Produc,Pedi_producId> {
    List<Pedi_Produc> findByIdPedidosId(Integer pedidosId);
    List<Pedi_Produc> findByIdProId(Integer proId);

}
