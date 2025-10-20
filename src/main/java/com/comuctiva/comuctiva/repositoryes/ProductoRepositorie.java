package com.comuctiva.comuctiva.repositoryes;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.comuctiva.comuctiva.models.Producto;

public interface ProductoRepositorie extends JpaRepository<Producto, Integer> {
	List<Producto> findByUsuario_NumDoc(Long numDoc);
}
