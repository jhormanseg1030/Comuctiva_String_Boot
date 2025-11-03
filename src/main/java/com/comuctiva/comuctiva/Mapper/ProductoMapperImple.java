package com.comuctiva.comuctiva.Mapper;

import org.springframework.stereotype.Component;

import com.comuctiva.comuctiva.Dto.ProductoCreateDto;
import com.comuctiva.comuctiva.Dto.ProductoDto;
import com.comuctiva.comuctiva.models.Producto;
import com.comuctiva.comuctiva.models.Unidad_Medida;
import com.comuctiva.comuctiva.repositoryes.Unidad_MedidaRepositories;

import jakarta.persistence.EntityNotFoundException;

@Component
public class ProductoMapperImple implements ProductoMapper{

   private final Unidad_MedidaRepositories unidad_MedidaRepositories;

   public ProductoMapperImple(Unidad_MedidaRepositories unidad_MedidaRepositories){
      this.unidad_MedidaRepositories = unidad_MedidaRepositories;
   }

   @Override
   public Producto toProducto(ProductoCreateDto productoCreateDto){
      Producto producto = new Producto();
      producto.setNomprod(productoCreateDto.getNombre_Producto());
      producto.setValor(productoCreateDto.getValor());
      producto.setCant(productoCreateDto.getCantidad());
      producto.setImagen(productoCreateDto.getImagen());
      producto.setDescrip(productoCreateDto.getDescripcion());
      producto.setCategoria(productoCreateDto.getCategoria());

      Unidad_Medida unidad_Medida = unidad_MedidaRepositories.findById(productoCreateDto.getId_medida())
      .orElseThrow(() -> new EntityNotFoundException("Unidad de medida no encontrada"));
      producto.setUnidad_Medida(unidad_Medida);
      return producto;
   }

@Override
public ProductoDto toProductoDto(Producto producto) {
    ProductoDto dto = new ProductoDto();
    dto.setId_pro(producto.getId_producto());
    dto.setNombre_Producto(producto.getNomprod()); // ✅ Cambiado
    dto.setValor(producto.getValor());                     // ✅ Cambiado
    dto.setCantidad(producto.getCant());               // ✅ Cambiado
    dto.setImagen(producto.getImagen());                   // ✅ Cambiado
    dto.setDescripcion(producto.getDescrip());         // ✅ Cambiado
    dto.setCategoria(producto.getCategoria());
    dto.setId_medida(producto.getUnidad_Medida() != null ? producto.getUnidad_Medida().getId_Medida() : null);
    return dto;
}
}