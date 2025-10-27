
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

    public Integer getId_Usuario() { return id_Usuario; }
    public void setId_Usuario(Integer id_Usuario) { this.id_Usuario = id_Usuario; }

    public String getNom_Usu() { return nom_Usu; }
    public void setNom_Usu(String nom_Usu) { this.nom_Usu = nom_Usu; }

    public String getApell1() { return apell1; }
    public void setApell1(String apell1) { this.apell1 = apell1; }

    public String getApell2() { return apell2; }
    public void setApell2(String apell2) { this.apell2 = apell2; }

    public Long getTel() { return tel; }
    public void setTel(Long tel) { this.tel = tel; }

    public Long getTel2() { return tel2; }
    public void setTel2(Long tel2) { this.tel2 = tel2; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public Long getNumDoc() { return numDoc; }
    public void setNumDoc(Long numDoc) { this.numDoc = numDoc; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Tip_Doc getTip_Doc() { return tip_Doc; }
    public void setTip_Doc(Tip_Doc tip_Doc) { this.tip_Doc = tip_Doc; }

    public List<Rol_Usuario> getRoles_de_usuarios() { return roles_de_usuarios; }
    public void setRoles_de_usuarios(List<Rol_Usuario> roles_de_usuarios) { this.roles_de_usuarios = roles_de_usuarios; }
}

