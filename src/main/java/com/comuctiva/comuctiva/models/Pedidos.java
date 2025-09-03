package com.comuctiva.comuctiva.models;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
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
@Entity
@Data
public class Pedidos {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id_pedido;
    private LocalDateTime fehor_pedi;

    @ManyToOne (optional = false)
    @JoinColumn (name = "id_Usuario", nullable = false, foreignKey = @ForeignKey(name = "FK_Usuario"))
    private Usuario usuario;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "id_guia", nullable = false, foreignKey = @ForeignKey(name ="FK_guia_envio"))
    private Guia_Envio guia_envio;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "id_Estado", nullable = false, foreignKey = @ForeignKey(name = "FK_Estado"))
    private Estado estado;

    @OneToMany(mappedBy = "pedi")
    private List<Pedi_Produc> pedido;
}
