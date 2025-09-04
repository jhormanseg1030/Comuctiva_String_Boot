package com.comuctiva.comuctiva.Mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.comuctiva.comuctiva.Dto.ProductoDto;
import com.comuctiva.comuctiva.models.Producto;

@Component
public class ProductoMapperImple implements ProductoMapper{
   @Override
      public Producto toProducto(ProductoDto productoDto){
         if(productoDto == null){
         return null;
         }
   Producto producto =new Producto();
   producto.setId_producto(productoDto.getId_pro());
   producto.setNomprod(productoDto.getNopro());
   producto.setValor(productoDto.getValoor());
   producto.setCant(productoDto.getCantid());
   producto.setImagen(productoDto.getImage());
   producto.setDescrip(productoDto.getDescri());
   return producto;
   }
   @Override
   public ProductoDto toProductoDto( Producto producto){
      if(producto == null){
         return null;
      }
      ProductoDto productoDto=new ProductoDto();
      productoDto.setId_pro(producto.getId_producto());
      productoDto.setNopro(producto.getNomprod());
      productoDto.setValoor(producto.getValor());
      productoDto.setCantid(producto.getCant());
      productoDto.setImage(producto.getImagen());
      productoDto.setDescri(producto.getDescrip());
      return productoDto;
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