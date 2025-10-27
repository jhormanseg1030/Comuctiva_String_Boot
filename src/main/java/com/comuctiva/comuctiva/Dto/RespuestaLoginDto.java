package com.comuctiva.comuctiva.Dto;

public class RespuestaLoginDto {
    private String token;
    private Integer id_Usuario;
    private String nom_Usu;
    private String apell1;
    private String apell2;
    private Long tel;
    private Long tel2;
    private String correo;
    private Long numDoc;
    private Integer tipDocId;
    private String tipDocNombre;
    private String rol;

    // Constructor vacío
    public RespuestaLoginDto() {}

    // Constructor completo
    public RespuestaLoginDto(String token, Integer id_Usuario, String nom_Usu, String apell1, String apell2, 
                            Long tel, Long tel2, String correo, Long numDoc, Integer tipDocId, String tipDocNombre, String rol) {
        this.token = token;
        this.id_Usuario = id_Usuario;
        this.nom_Usu = nom_Usu;
        this.apell1 = apell1;
        this.apell2 = apell2;
        this.tel = tel;
        this.tel2 = tel2;
        this.correo = correo;
        this.numDoc = numDoc;
        this.tipDocId = tipDocId;
        this.tipDocNombre = tipDocNombre;
        this.rol = rol;
    }

    // Getters y Setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

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

    public Integer getTipDocId() { return tipDocId; }
    public void setTipDocId(Integer tipDocId) { this.tipDocId = tipDocId; }

    public String getTipDocNombre() { return tipDocNombre; }
    public void setTipDocNombre(String tipDocNombre) { this.tipDocNombre = tipDocNombre; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
}
