package com.comuctiva.comuctiva.Mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

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
   public Producto toProducto(ProductoDto productoDto){
      Producto producto = new Producto();
      producto.setId_producto(productoDto.getId_pro());
      producto.setNomprod(productoDto.getNopro());
      producto.setValor(productoDto.getValoor());
      producto.setCant(productoDto.getCantid());
      producto.setImagen(productoDto.getImage());
      producto.setDescrip(productoDto.getDescri());

      Tienda tienda = tiendaRepositories.findById(productoDto.getId_tien())
      .orElseThrow(() -> new EntityNotFoundException("Tienda no encontrada"));
      producto.setTienda(tienda);

      Unidad_Medida unidad_Medida = unidad_MedidaRepositories.findById(productoDto.getId_medi())
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
   @Override
   public List<ProductoDto> toProductoDtoList(List<Producto>product){
      if(product==null){
         return List.of();
      }
      List<ProductoDto> productoDtos=new ArrayList<ProductoDto>(product.size());
      for(Producto producto : product){
         productoDtos.add(toProductoDto(producto));
      }
      return productoDtos;
   }
   @Override
   public  void updateProducto(Producto producto, ProductoDto productoDto){
      if (productoDto==null) {
         return;
      }
      producto.setId_producto(productoDto.getId_pro());
      producto.setNomprod(productoDto.getNopro());
      producto.setValor(productoDto.getValoor());
      producto.setCant(productoDto.getCantid());
      producto.setImagen(productoDto.getImage());
      producto.setDescrip(productoDto.getDescri());
   }

}