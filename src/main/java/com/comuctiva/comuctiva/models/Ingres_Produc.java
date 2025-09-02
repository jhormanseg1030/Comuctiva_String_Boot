package com.comuctiva.comuctiva.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Ingres_Produc {
    @Id
    private Short cant;
    /*
    @Embeddable
    public class Ingres_ProducId implements Serializable{
        private Long id_ingresos;
        private Long id_producto;
    }
    @EmbeddedId
    private Ingres_ProducId id = new Ingres_ProducId();

    @ManyToOne
    @MapsId("id_ingresos")
    private Ingresos ingresos;

    @ManyToOne
    @MapsId("id_producto")
    private Producto producto;

    
    @NotNull
    @Column(name = "fecha_asignacion")
    private LocalDate fechaAsignacion;

    @Size(max = 500)
    @Column(name = "observaciones")
    private String observaciones;
    */

}
