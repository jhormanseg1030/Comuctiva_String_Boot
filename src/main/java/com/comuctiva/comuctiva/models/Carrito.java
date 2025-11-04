package com.comuctiva.comuctiva.models;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "carrito")
public class Carrito {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrito")
    private Integer idCarrito;
    
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private Timestamp fechaCreacion = new Timestamp(System.currentTimeMillis());
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Id_Usuario", nullable = false, unique = true, foreignKey = @ForeignKey(name = "FK_Usuario_Carrito"))
    private Usuario usuario;

    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Produc_Carri> items = new ArrayList<>();
}
