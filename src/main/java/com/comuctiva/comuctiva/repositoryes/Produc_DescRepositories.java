package com.comuctiva.comuctiva.repositoryes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comuctiva.comuctiva.models.Produc_Desc;
import com.comuctiva.comuctiva.models.Produc_DescId;

@Repository
public interface Produc_DescRepositories extends JpaRepository <Produc_Desc,Produc_DescId>{
    List <Produc_Desc> findByIdProduId(Integer produId);
    List <Produc_Desc> findByIdDescuId(Integer descuId);
}
