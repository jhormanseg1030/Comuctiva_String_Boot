package com.comuctiva.comuctiva.models;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Calilficaciones_produc {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id_calificaciones;

    @Column(length = 50)
    private String comentario;
    @Column()
    private LocalDateTime fecha_calificacion;
    @Column()
    private Short estrellas;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_producto", nullable = false, foreignKey = @ForeignKey(name = "FK_Producto"))
    private Producto producto;

    @OneToOne(cascade =CascadeType.ALL)
    @JoinColumn(name = "id_Usuario", nullable = false, foreignKey = @ForeignKey(name = "FK_Usur"))
    private Usuario usuario;
}
