package com.comuctiva.comuctiva.Dto;

public class LoginRequest {
    private Integer tipDocId;
    private Long numDoc;
    private String password;

    public Integer getTipDocId() { return tipDocId; }
    public void setTipDocId(Integer tipDocId) { this.tipDocId = tipDocId; }
    public Long getNumDoc() { return numDoc; }
    public void setNumDoc(Long numDoc) { this.numDoc = numDoc; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
