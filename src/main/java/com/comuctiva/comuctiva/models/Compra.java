package com.comuctiva.comuctiva.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Compra {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id_compra;
    private Short total;
    @Column (length = 30)
    private String ref_pago;
    private LocalDateTime fec_com;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "id_pedido", nullable = false, foreignKey = @ForeignKey(name = "FK_Pedido"))
    private Pedidos pedido;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "id_TiPago", nullable = false, foreignKey = @ForeignKey(name = "FK_TiPago"))
    private Tipo_De_Pago tipo_pago;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comp_Produc> produ = new ArrayList<>();

}
