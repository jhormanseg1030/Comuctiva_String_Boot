package com.comuctiva.comuctiva.Mapper;

import org.springframework.stereotype.Component;

import com.comuctiva.comuctiva.Dto.Califi_ProduCreateDto;
import com.comuctiva.comuctiva.Dto.Califi_ProduDto;
import com.comuctiva.comuctiva.models.Calificaciones_produc;
import com.comuctiva.comuctiva.models.Producto;
import com.comuctiva.comuctiva.models.Usuario;
import com.comuctiva.comuctiva.repositoryes.ProductoRepositorie;
import com.comuctiva.comuctiva.repositoryes.UsuarioRepositories;

import jakarta.persistence.EntityNotFoundException;

@Component
public class Califi_ProducMapperImple implements Califi_ProducMapper {

    private final ProductoRepositorie productoRepositorie;
    private final UsuarioRepositories usuarioRepositories;

    public Califi_ProducMapperImple (ProductoRepositorie productoRepositorie, UsuarioRepositories usuarioRepositories){
        this.productoRepositorie = productoRepositorie;
        this.usuarioRepositories = usuarioRepositories;
    }

    @Override
    public Calificaciones_produc toCalilficaciones_produc(Califi_ProduCreateDto califi_ProduCreateDto){
        if (califi_ProduCreateDto == null) {
            return null;
        }

        Calificaciones_produc calilficaciones_produc = new Calificaciones_produc();
        calilficaciones_produc.setComentario(califi_ProduCreateDto.getComent());
        calilficaciones_produc.setFecha_calificacion(califi_ProduCreateDto.getFec_calif());
        calilficaciones_produc.setEstrellas(califi_ProduCreateDto.getEstre());

        Producto producto = productoRepositorie.findById(califi_ProduCreateDto.getId_produ())
        .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
        calilficaciones_produc.setProducto(producto);

        Usuario usuario = usuarioRepositories.findById(califi_ProduCreateDto.getId_usua())
        .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        calilficaciones_produc.setUsuario(usuario);
        return calilficaciones_produc;
    }

    @Override
    public Califi_ProduDto toCalifi_ProduDto(Calificaciones_produc calilficaciones_produc){
        return new Califi_ProduDto(
            calilficaciones_produc.getId_calificaciones(),
            calilficaciones_produc.getComentario(),
            calilficaciones_produc.getFecha_calificacion(),
            calilficaciones_produc.getEstrellas(),
            calilficaciones_produc.getProducto().getId_producto(),
            calilficaciones_produc.getUsuario().getId_Usuario());
    }
}