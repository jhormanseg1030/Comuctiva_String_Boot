package com.comuctiva.comuctiva.Mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.comuctiva.comuctiva.Dto.Califi_ProduDto;
import com.comuctiva.comuctiva.models.Calilficaciones_produc;
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
    public Calilficaciones_produc toCalilficaciones_produc (Califi_ProduDto califi_ProduDto){
        Calilficaciones_produc calilficaciones_produc = new Calilficaciones_produc();
        calilficaciones_produc.setId_calificaciones(califi_ProduDto.getId_califi());
        calilficaciones_produc.setComentario(califi_ProduDto.getComent());
        calilficaciones_produc.setFecha_calificacion(califi_ProduDto.getFec_calif());
        calilficaciones_produc.setEstrellas(califi_ProduDto.getEstre());

        Producto producto = productoRepositorie.findById(califi_ProduDto.getId_produ())
        .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
        calilficaciones_produc.setProducto(producto);

        Usuario usuario = usuarioRepositories.findById(califi_ProduDto.getId_usua())
        .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        calilficaciones_produc.setUsuario(usuario);
        return calilficaciones_produc;
    }

    @Override
    public Califi_ProduDto toCalifi_ProduDto(Calilficaciones_produc calilficaciones_produc){
        return new Califi_ProduDto(
            calilficaciones_produc.getId_calificaciones(),
            calilficaciones_produc.getComentario(),
            calilficaciones_produc.getFecha_calificacion(),
            calilficaciones_produc.getEstrellas(),
            calilficaciones_produc.getProducto().getId_producto(),
            calilficaciones_produc.getUsuario().getId_Usuario());
    }

    @Override
    public List<Califi_ProduDto> toCalifi_ProduDtoList(List<Calilficaciones_produc>calilficaciones_produc2){
        if (calilficaciones_produc2 == null){
            return List.of();
        }
        List<Califi_ProduDto>califi_ProduDto = new ArrayList<Califi_ProduDto>(calilficaciones_produc2.size());
        for (Calilficaciones_produc calilficaciones_produc : calilficaciones_produc2){
            califi_ProduDto.add(toCalifi_ProduDto(calilficaciones_produc));
        }
        return califi_ProduDto;
    }

    @Override
    public void updateCalilficaciones_produc(Calilficaciones_produc calilficaciones_produc, Califi_ProduDto califi_ProduDto){
        if (califi_ProduDto == null){
            return;
        }
        calilficaciones_produc.setId_calificaciones(califi_ProduDto.getId_califi());
        calilficaciones_produc.setComentario(califi_ProduDto.getComent());
        calilficaciones_produc.setFecha_calificacion(califi_ProduDto.getFec_calif());
        calilficaciones_produc.setEstrellas(califi_ProduDto.getEstre());
    }
}