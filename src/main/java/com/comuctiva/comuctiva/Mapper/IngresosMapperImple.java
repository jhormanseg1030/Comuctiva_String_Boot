package com.comuctiva.comuctiva.Mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.comuctiva.comuctiva.Dto.IngresosDto;
import com.comuctiva.comuctiva.models.Ingresos;
import com.comuctiva.comuctiva.models.Usuario;
import com.comuctiva.comuctiva.repositoryes.UsuarioRepositories;

import jakarta.persistence.EntityNotFoundException;

@Component
public class IngresosMapperImple implements IngresosMapper{
    
    private final UsuarioRepositories usuarioRepositories;

    public IngresosMapperImple(UsuarioRepositories usuarioRepositories){
        this.usuarioRepositories = usuarioRepositories;
    }

    @Override
    public Ingresos toIngresos(IngresosDto ingresosDto){
        Ingresos ingresos = new Ingresos();
        ingresos.setId_ingreso(ingresosDto.getId_ingre());
        ingresos.setFecha(ingresosDto.getFech());
        ingresos.setOrbser(ingresosDto.getObser());

        Usuario usuario = usuarioRepositories.findById(ingresosDto.getId_usu())
        .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado canson"));
        ingresos.setUsuario(usuario);
        return ingresos;
    }

    @Override
    public IngresosDto toIngresosDto(Ingresos ingresos){
        return new IngresosDto(
            ingresos.getId_ingreso(),
            ingresos.getOrbser(),
            ingresos.getFecha(),
            ingresos.getUsuario().getId_Usuario());
    }

    @Override
    public List<IngresosDto>toIngresosDtoList(List<Ingresos>ingresoss){
        if(ingresoss==null){
            return List.of();
        }
        List<IngresosDto> ingresosDtos = new ArrayList<IngresosDto>(ingresoss.size());
        for(Ingresos ingresos: ingresoss){
        ingresosDtos.add(toIngresosDto(ingresos));
        }
        return ingresosDtos;
}

@Override
public void updateIngresos(Ingresos ingresos,IngresosDto ingresosDto){
    if(ingresosDto == null){
        return;
    }
    ingresos.setId_ingreso(ingresosDto.getId_ingre());
    ingresos.setOrbser(ingresosDto.getObser());
    ingresos.setFecha(ingresos.getFecha());
}
}
