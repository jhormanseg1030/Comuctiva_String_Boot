package com.comuctiva.comuctiva.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Comentarios")
public class Comentarios {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Comentario")
    private Integer idComentario;

    @Column(name = "Comentario", nullable = false, columnDefinition = "TEXT")
    private String comentario;
    
    @Column(name = "Fecha_Comentario")
    private LocalDateTime fechaComentario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Comp_Produc", referencedColumnName = "ID_Com_Produc", nullable = false)
    private Comp_Produc compProduc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Usuario", nullable = false, foreignKey = @ForeignKey(name = "fk_comentario_usuario"))
    private Usuario usuario;

    // Campos adicionales para mantener compatibilidad con la estructura de la BD
    @Column(name = "id_compra", nullable = false)
    private Integer idCompra;

    @Column(name = "id_producto", nullable = false)
    private Integer idProducto;
}
