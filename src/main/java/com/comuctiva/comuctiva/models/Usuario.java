package com.comuctiva.comuctiva.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Usuario;
    @Column(length = 50)
    private String nom_Usu;
    @Column( nullable = false ,length = 50)
    private String apell1;
    @Column(length = 50)
    private String apell2;
    private Long tel;
    @Column(nullable = false)
    private Long tel2;
    @Column(length = 50)
    private String correo;
    private Long numDoc;
    private String password;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipdocu", nullable = false, foreignKey = @ForeignKey(name = "FK_Tip_Doc"))
    private Tip_Doc tip_Doc;

    @OneToMany(mappedBy = "usuario")
    private List<Rol_Usuario> roles_de_usuarios;

}

