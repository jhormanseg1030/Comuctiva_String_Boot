package com.comuctiva.comuctiva.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.comuctiva.comuctiva.Dto.Pedi_ProducDto;
import com.comuctiva.comuctiva.Dto.PedidosAsignadosDto;
import com.comuctiva.comuctiva.Dto.ProductoAsignado2Dto;
import com.comuctiva.comuctiva.Mapper.Pedi_ProducMapper;
import com.comuctiva.comuctiva.models.Pedi_Produc;
import com.comuctiva.comuctiva.models.Pedi_producId;
import com.comuctiva.comuctiva.repositoryes.Pedi_ProducRepositories;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class Pedi_ProducServicesImple implements Pedi_ProducServices {

    private final Pedi_ProducRepositories repositories;
    private final Pedi_ProducMapper mapper;

    public Pedi_ProducServicesImple(Pedi_ProducRepositories repositories, Pedi_ProducMapper mapper){
        this.repositories = repositories;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public Pedi_ProducDto asignar(Pedi_ProducDto pedi_p){

        Pedi_Produc pe = mapper.toPedi_Produc(pedi_p);

        // Verificar si ya existe

        if (repositories.existsById(pe.getId())) {
            throw new IllegalStateException("El producto ya está asignado al pedido");
        }

        Pedi_Produc guardar = repositories.save(pe);
        return mapper.toPedi_ProducDto(guardar);
    }

    @Override
    public List<Pedi_ProducDto> listarPedi(Integer pedidosId){
        return repositories.findByIdProId(pedidosId)
        .stream()
        .map(mapper:: toPedi_ProducDto)
        .collect(Collectors.toList());
    }

    @Override
    public List<PedidosAsignadosDto> listarPedi2(Integer pedidosId){
        return repositories.findByIdPedidosId(pedidosId)
        .stream()
        .map(mapper :: toPedi_AsigDto)
        .collect(Collectors.toList());
    }

    @Override
    public List<Pedi_ProducDto> listarProduc(Integer proId){
        return repositories.findByIdProId(proId)
        .stream()
        .map(mapper :: toPedi_ProducDto)
        .collect(Collectors.toList());
    }

    @Override
    public List<ProductoAsignado2Dto> listarProduc2AsignadosDtos(Integer proId){
        return repositories.findByIdProId(proId)
        .stream()
        .map(mapper :: toProd_Asig)
        .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Integer pedidosId, Integer proId){
        Pedi_producId id = new Pedi_producId(pedidosId, proId);
        if (!repositories.existsById(id)) {
            throw new EntityNotFoundException("Pedido o Producto no encontrado");
         }
        repositories.deleteById(id);
    }
}
