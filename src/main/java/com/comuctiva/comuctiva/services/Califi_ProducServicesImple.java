package com.comuctiva.comuctiva.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comuctiva.comuctiva.Dto.Califi_ProduCreateDto;
import com.comuctiva.comuctiva.Dto.Califi_ProduDto;
import com.comuctiva.comuctiva.Dto.CalificacionUpdateDto;
import com.comuctiva.comuctiva.Mapper.Califi_ProducMapper;
import com.comuctiva.comuctiva.models.Calilficaciones_produc;
import com.comuctiva.comuctiva.models.Producto;
import com.comuctiva.comuctiva.models.Usuario;
import com.comuctiva.comuctiva.repositoryes.Calificaciones_producRespositories;
import com.comuctiva.comuctiva.repositoryes.ProductoRepositorie;
import com.comuctiva.comuctiva.repositoryes.UsuarioRepositories;

@Service
public class Califi_ProducServicesImple implements Califi_ProducServices {
    
    private final Calificaciones_producRespositories califi_Respository;
    private final Califi_ProducMapper califi_ProducMapper;
    private final UsuarioRepositories usuarioRepo;
    private final ProductoRepositorie productoRepo;

    public Califi_ProducServicesImple(Calificaciones_producRespositories califi_Respository, Califi_ProducMapper califi_ProducMapper, UsuarioRepositories usuarioRepo, ProductoRepositorie productoRepo) {
        this.califi_Respository = califi_Respository;
        this.califi_ProducMapper = califi_ProducMapper;
        this.usuarioRepo = usuarioRepo;
        this.productoRepo = productoRepo;
    }

    @Override
    @Transactional
    public Califi_ProduDto crearCalif_Produ(Califi_ProduCreateDto califi_ProduCreateDto) {
        Calilficaciones_produc califi = califi_ProducMapper.toCalilficaciones_produc(califi_ProduCreateDto);
        Calilficaciones_produc califiGuardado = califi_Respository.save(califi);
        return califi_ProducMapper.toCalifi_ProduDto(califiGuardado);
    }

    @Override
    @Transactional()
    public Califi_ProduDto califi_ProduPorId(Integer id) {
        Calilficaciones_produc califi_Produ = califi_Respository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Califi_Produ no encontrado con id:"));
        return califi_ProducMapper.toCalifi_ProduDto(califi_Produ);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Califi_ProduDto> listar() {
        return califi_Respository.findAll()
                .stream()
                .map(califi_ProducMapper::toCalifi_ProduDto)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminarCalif_Produ(Integer id) {
        Calilficaciones_produc califi_ProduDtoElimi = califi_Respository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Califi_Produ no encontrado con id: " + id));
        califi_Respository.save(califi_ProduDtoElimi);
    }

    @Override
    @Transactional
    public Califi_ProduDto actualizarCalif_Produ(CalificacionUpdateDto califiUpdate) {
        Calilficaciones_produc califi_Produ = califi_Respository.findById(califiUpdate.getId_califi())
                .orElseThrow(() -> new IllegalStateException("Califi_Produ no encontrado con id: "));

        califi_Produ.setComentario(califiUpdate.getComent());
        califi_Produ.setFecha_calificacion(califiUpdate.getFec_calif());
        califi_Produ.setEstrellas(califiUpdate.getEstre());

        Producto producto = productoRepo.findById(califiUpdate.getId_produ())
                .orElseThrow(() -> new IllegalStateException("Producto no encontrado"));
        califi_Produ.setProducto(producto);

        Usuario usuario = usuarioRepo.findById(califiUpdate.getId_usua())
                .orElseThrow(() -> new IllegalStateException("Usuario no encontrado"));
        califi_Produ.setUsuario(usuario);

        Calilficaciones_produc califi_ProduDtoGuardado = califi_Respository.save(califi_Produ);
        return califi_ProducMapper.toCalifi_ProduDto(califi_ProduDtoGuardado);
    }
}
