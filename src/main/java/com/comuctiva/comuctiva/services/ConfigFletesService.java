package com.comuctiva.comuctiva.services;

import com.comuctiva.comuctiva.Dto.ConfigFletesDto;
import com.comuctiva.comuctiva.Dto.ReglasGeneralesDto;
import com.comuctiva.comuctiva.Dto.TarifaVehiculoDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ConfigFletesService {
    
    public ConfigFletesDto obtenerConfiguracion() {
        ConfigFletesDto config = new ConfigFletesDto();
        
        // Configuración de vehículos
        Map<String, TarifaVehiculoDto> vehiculos = new HashMap<>();
        
        TarifaVehiculoDto furgon = new TarifaVehiculoDto();
        furgon.setNombre("Furgón");
        furgon.setIcono("🚚");
        furgon.setTarifaBase(20000.0);
        furgon.setCostoKm(2500.0);
        furgon.setMaxDistancia(300);
        furgon.setCapacidadKg(1500);
        furgon.setDescripcion("Ideal para cargas medianas y productos empaquetados");
        vehiculos.put("furgon", furgon);
        
        TarifaVehiculoDto van = new TarifaVehiculoDto();
        van.setNombre("Van de Carga");
        van.setIcono("🚐");
        van.setTarifaBase(35000.0);
        van.setCostoKm(3200.0);
        van.setMaxDistancia(500);
        van.setCapacidadKg(3000);
        van.setDescripcion("Perfecto para grandes volúmenes y productos a granel");
        vehiculos.put("van", van);
        
        config.setVehiculos(vehiculos);
        
        // Reglas generales
        ReglasGeneralesDto reglas = new ReglasGeneralesDto();
        reglas.setSeguroPct(0.02);          // 2%
        reglas.setIvaPct(0.19);             // 19%
        reglas.setPeajeEstimadoPorKm(120.0); // COP 120 por km
        reglas.setRecargoUrgenciaPct(0.15);  // 15%
        reglas.setFactorCargaPesada(1.2);    // 20% adicional
        
        config.setReglas(reglas);
        
        return config;
    }
}
