package com.comuctiva.comuctiva.Mapper;

import org.springframework.stereotype.Component;

import com.comuctiva.comuctiva.Dto.ProductoCreateDto;
import com.comuctiva.comuctiva.Dto.ProductoDto;
import com.comuctiva.comuctiva.models.Producto;
import com.comuctiva.comuctiva.models.Tienda;
import com.comuctiva.comuctiva.models.Unidad_Medida;
import com.comuctiva.comuctiva.repositoryes.TiendaRepositories;
import com.comuctiva.comuctiva.repositoryes.Unidad_MedidaRepositories;

import jakarta.persistence.EntityNotFoundException;

@Component
public class ProductoMapperImple implements ProductoMapper{

   private final Unidad_MedidaRepositories unidad_MedidaRepositories;
   private final TiendaRepositories tiendaRepositories;

   public ProductoMapperImple(Unidad_MedidaRepositories unidad_MedidaRepositories, TiendaRepositories tiendaRepositories){
      this.unidad_MedidaRepositories = unidad_MedidaRepositories;
      this.tiendaRepositories = tiendaRepositories;
   }

   @Override
   public Producto toProducto(ProductoCreateDto productoCreateDto){
      Producto producto = new Producto();
      producto.setNomprod(productoCreateDto.getNombre_Producto());
      producto.setValor(productoCreateDto.getValor());
      producto.setCant(productoCreateDto.getCantidad());
      producto.setImagen(productoCreateDto.getImagen());
      producto.setDescrip(productoCreateDto.getDescripcion());

      Tienda tienda = tiendaRepositories.findById(productoCreateDto.getId_tienda())
      .orElseThrow(() -> new EntityNotFoundException("Tienda no encontrada"));
      producto.setTienda(tienda);

      Unidad_Medida unidad_Medida = unidad_MedidaRepositories.findById(productoCreateDto.getId_medida())
      .orElseThrow(() -> new EntityNotFoundException("Unidad de medida no encontrada"));
      producto.setUnidad_Medida(unidad_Medida);
      return producto;
   }

   @Override
   public ProductoDto toProductoDto(Producto producto){
      return new ProductoDto(
         producto.getId_producto(),
         producto.getNomprod(),
         producto.getValor(),
         producto.getCant(),
         producto.getImagen(),
         producto.getDescrip(),
         producto.getUnidad_Medida().getId_Medida(),
         producto.getTienda().getID_Tienda()
      );
   }
}