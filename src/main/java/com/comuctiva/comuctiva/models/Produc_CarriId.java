package com.comuctiva.comuctiva.models;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produc_CarriId implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Column(name = "carrito_id")
    private Integer carritoId;
    
    @Column(name = "producto_id")
    private Integer prodId;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produc_CarriId that = (Produc_CarriId) o;
        return Objects.equals(carritoId, that.carritoId) &&
            Objects.equals(prodId, that.prodId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(carritoId, prodId);
    }
}

