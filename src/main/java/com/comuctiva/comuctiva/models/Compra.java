package com.comuctiva.comuctiva.models;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Compra")
    private Integer id_compra;
    
    @Column(name = "total")
    private Double total;
    
    @Column(name = "Ref_Pago", length = 30)
    private String ref_pago;
    
    @Column(name = "Fec_com")
    private LocalDateTime fec_com;
    
    // ✅ USAR LOS NOMBRES CORRECTOS DE LA BD
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Pedido", nullable = false)
    private Pedidos pedido;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TiPago")
    private Tipo_De_Pago tipo_pago;
    
    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comp_Produc> productos;
}
