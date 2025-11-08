package com.comuctiva.comuctiva.services;

import com.comuctiva.comuctiva.Dto.*;
import com.comuctiva.comuctiva.models.*;
import com.comuctiva.comuctiva.repositoryes.CotizacionRepositories;
import com.comuctiva.comuctiva.repositoryes.TransportadoraRepositories;
import com.comuctiva.comuctiva.repositoryes.UsuarioRepositories;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CotizacionService {
    
    private final CotizacionRepositories cotizacionRepositories;
    private final ConfigFletesService configFletesService;
    private final TransportadoraRepositories transportadoraRepositories;
    private final UsuarioRepositories usuarioRepositories;
    
    public CotizacionService(CotizacionRepositories cotizacionRepositories, 
                            ConfigFletesService configFletesService,
                            TransportadoraRepositories transportadoraRepositories,
                            UsuarioRepositories usuarioRepositories) {
        this.cotizacionRepositories = cotizacionRepositories;
        this.configFletesService = configFletesService;
        this.transportadoraRepositories = transportadoraRepositories;
        this.usuarioRepositories = usuarioRepositories;
    }
    
    public CotizacionCalculoResponse calcular(CotizacionCalculoRequest request) {
        ConfigFletesDto config = configFletesService.obtenerConfiguracion();
        
        // Obtener tarifa del tipo de vehículo
        String tipoKey = request.getTipoVehiculo().name().toLowerCase();
        TarifaVehiculoDto tarifa = config.getVehiculos().get(tipoKey);
        
        if (tarifa == null) {
            throw new IllegalArgumentException("Tipo de vehículo no configurado: " + request.getTipoVehiculo());
        }
        
        // Validar distancia máxima
        if (request.getDistanciaKm() > tarifa.getMaxDistancia()) {
            CotizacionCalculoResponse response = new CotizacionCalculoResponse();
            response.setMensaje("La distancia máxima para " + tarifa.getNombre() + " es " + tarifa.getMaxDistancia() + " km");
            response.setTotal(0.0);
            return response;
        }
        
        // Validar capacidad
        if (request.getPesoKg() > tarifa.getCapacidadKg()) {
            CotizacionCalculoResponse response = new CotizacionCalculoResponse();
            response.setMensaje("El peso máximo para " + tarifa.getNombre() + " es " + tarifa.getCapacidadKg() + " kg");
            response.setTotal(0.0);
            return response;
        }
        
        ReglasGeneralesDto reglas = config.getReglas();
        DetallesCotizacionDto detalles = new DetallesCotizacionDto();
        
        // Calcular costos
        detalles.setBase(tarifa.getTarifaBase());
        detalles.setDistancia(tarifa.getCostoKm() * request.getDistanciaKm());
        
        double subtotalSinRecargos = detalles.getBase() + detalles.getDistancia();
        
        detalles.setSeguro(subtotalSinRecargos * reglas.getSeguroPct());
        detalles.setPeajes(reglas.getPeajeEstimadoPorKm() * request.getDistanciaKm());
        
        // Recargo por urgencia
        if (Boolean.TRUE.equals(request.getUrgente())) {
            detalles.setUrgencia(subtotalSinRecargos * reglas.getRecargoUrgenciaPct());
        } else {
            detalles.setUrgencia(0.0);
        }
        
        // Factor de carga pesada (> 70% capacidad)
        double porcentajeCarga = (double) request.getPesoKg() / tarifa.getCapacidadKg();
        if (porcentajeCarga > 0.7) {
            detalles.setFactorCarga(reglas.getFactorCargaPesada());
        } else {
            detalles.setFactorCarga(1.0);
        }
        
        // Subtotal
        double subtotal = (subtotalSinRecargos + detalles.getSeguro() + 
                          detalles.getPeajes() + detalles.getUrgencia()) * detalles.getFactorCarga();
        detalles.setSubtotal(subtotal);
        
        // IVA
        detalles.setIva(subtotal * reglas.getIvaPct());
        
        // Total
        double total = subtotal + detalles.getIva();
        
        CotizacionCalculoResponse response = new CotizacionCalculoResponse();
        response.setDetalles(detalles);
        response.setTotal(total);
        response.setMensaje("Cotización calculada exitosamente");
        
        return response;
    }
    
    @Transactional
    public CotizacionDto crear(CotizacionCreateDto createDto) {
        // Calcular primero
        CotizacionCalculoRequest calcRequest = new CotizacionCalculoRequest();
        calcRequest.setProducto(createDto.getProducto());
        calcRequest.setPesoKg(createDto.getPesoKg());
        calcRequest.setTipoVehiculo(createDto.getTipoVehiculo());
        calcRequest.setOrigen(createDto.getOrigen());
        calcRequest.setDestino(createDto.getDestino());
        calcRequest.setDistanciaKm(createDto.getDistanciaKm());
        calcRequest.setUrgente(createDto.getUrgente());
        
        CotizacionCalculoResponse calculo = calcular(calcRequest);
        
        if (calculo.getTotal() == 0.0) {
            throw new IllegalArgumentException(calculo.getMensaje());
        }
        
        // Crear cotización
        Cotizacion cotizacion = new Cotizacion();
        cotizacion.setProducto(createDto.getProducto());
        cotizacion.setPesoKg(createDto.getPesoKg().doubleValue());
        cotizacion.setTipoVehiculo(createDto.getTipoVehiculo());
        cotizacion.setOrigen(createDto.getOrigen());
        cotizacion.setDestino(createDto.getDestino());
        cotizacion.setDistanciaKm(createDto.getDistanciaKm().doubleValue());
        cotizacion.setEstado(EstadoCotizacion.PENDIENTE);
        cotizacion.setFecha(LocalDateTime.now());
        
        // Mapear detalles
        DetallesCotizacion detallesEntity = new DetallesCotizacion();
        detallesEntity.setBase(calculo.getDetalles().getBase());
        detallesEntity.setDistancia(calculo.getDetalles().getDistancia());
        detallesEntity.setSeguro(calculo.getDetalles().getSeguro());
        detallesEntity.setPeajes(calculo.getDetalles().getPeajes());
        detallesEntity.setUrgencia(calculo.getDetalles().getUrgencia());
        detallesEntity.setFactorCarga(calculo.getDetalles().getFactorCarga());
        detallesEntity.setSubtotal(calculo.getDetalles().getSubtotal());
        detallesEntity.setIva(calculo.getDetalles().getIva());
        cotizacion.setDetalles(detallesEntity);
        
        cotizacion.setTotal(calculo.getTotal());
        
        // Relaciones opcionales
        if (createDto.getId_transportadora() != null) {
            Transportadora transportadora = transportadoraRepositories.findById(createDto.getId_transportadora())
                    .orElseThrow(() -> new EntityNotFoundException("Transportadora no encontrada"));
            cotizacion.setTransportadora(transportadora);
        }
        
        if (createDto.getId_usuario() != null) {
            Usuario usuario = usuarioRepositories.findById(createDto.getId_usuario())
                    .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
            cotizacion.setUsuario(usuario);
        }
        
        Cotizacion guardada = cotizacionRepositories.save(cotizacion);
        return toDto(guardada);
    }
    
    public List<CotizacionDto> listarConFiltros(LocalDateTime inicio, LocalDateTime fin, EstadoCotizacion estado) {
        List<Cotizacion> cotizaciones;
        
        if (inicio != null && fin != null && estado != null) {
            cotizaciones = cotizacionRepositories.findByFechaBetweenAndEstado(inicio, fin, estado);
        } else if (inicio != null && fin != null) {
            cotizaciones = cotizacionRepositories.findByFechaBetween(inicio, fin);
        } else if (estado != null) {
            cotizaciones = cotizacionRepositories.findByEstado(estado);
        } else {
            cotizaciones = cotizacionRepositories.findAll();
        }
        
        return cotizaciones.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
    
    public CotizacionDto buscarPorId(Integer id) {
        Cotizacion cotizacion = cotizacionRepositories.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cotización no encontrada con ID: " + id));
        return toDto(cotizacion);
    }
    
    @Transactional
    public CotizacionDto cambiarEstado(Integer id, CotizacionEstadoDto estadoDto) {
        Cotizacion cotizacion = cotizacionRepositories.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cotización no encontrada con ID: " + id));
        
        cotizacion.setEstado(estadoDto.getEstado());
        
        if (estadoDto.getMotivoRechazo() != null) {
            cotizacion.setMotivoRechazo(estadoDto.getMotivoRechazo());
        }
        
        Cotizacion actualizada = cotizacionRepositories.save(cotizacion);
        return toDto(actualizada);
    }
    
    private CotizacionDto toDto(Cotizacion cotizacion) {
        CotizacionDto dto = new CotizacionDto();
        dto.setId_cotizacion(cotizacion.getId_cotizacion());
        dto.setFecha(cotizacion.getFecha());
        dto.setProducto(cotizacion.getProducto());
        dto.setPesoKg(cotizacion.getPesoKg() != null ? cotizacion.getPesoKg().intValue() : 0);
        dto.setTipoVehiculo(cotizacion.getTipoVehiculo());
        dto.setOrigen(cotizacion.getOrigen());
        dto.setDestino(cotizacion.getDestino());
        dto.setDistanciaKm(cotizacion.getDistanciaKm() != null ? cotizacion.getDistanciaKm().intValue() : 0);
        dto.setEstado(cotizacion.getEstado());
        dto.setTotal(cotizacion.getTotal());
        dto.setMotivoRechazo(cotizacion.getMotivoRechazo());
        
        if (cotizacion.getDetalles() != null) {
            DetallesCotizacionDto detallesDto = new DetallesCotizacionDto();
            detallesDto.setBase(cotizacion.getDetalles().getBase());
            detallesDto.setDistancia(cotizacion.getDetalles().getDistancia());
            detallesDto.setSeguro(cotizacion.getDetalles().getSeguro());
            detallesDto.setPeajes(cotizacion.getDetalles().getPeajes());
            detallesDto.setUrgencia(cotizacion.getDetalles().getUrgencia());
            detallesDto.setFactorCarga(cotizacion.getDetalles().getFactorCarga());
            detallesDto.setSubtotal(cotizacion.getDetalles().getSubtotal());
            detallesDto.setIva(cotizacion.getDetalles().getIva());
            dto.setDetalles(detallesDto);
        }
        
        if (cotizacion.getTransportadora() != null) {
            dto.setId_transportadora(cotizacion.getTransportadora().getId_transpor());
            dto.setNombreTransportadora(cotizacion.getTransportadora().getNombre());
        }
        
        if (cotizacion.getUsuario() != null) {
            dto.setId_usuario(cotizacion.getUsuario().getId_Usuario());
            dto.setNombreUsuario(cotizacion.getUsuario().getNom_Usu());
        }
        
        return dto;
    }
}
