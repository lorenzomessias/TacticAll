/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

/**
 *
 * @author Everymind
 */
public class Time {
    private int id;
    private String nome;
    private String sigla;
    private String pais;
    private String liga;
    private int idUsuario;
    private String corUniforme;
    public Time(){}
    public Time(int id, String nome, String sigla, String pais, String liga, int idUsuario, String corUniforme) {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
        this.pais = pais;
        this.liga = liga;
        this.idUsuario = idUsuario;
        this.corUniforme = corUniforme;
    }
    public Time( String nome, String sigla, String pais, String liga, int idUsuario, String corUniforme) {
        this.nome = nome;
        this.sigla = sigla;
        this.pais = pais;
        this.liga = liga;
        this.idUsuario = idUsuario;
        this.corUniforme = corUniforme;
    }
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSigla() {
        return sigla;
    }

    public String getPais() {
        return pais;
    }

    public String getLiga() {
        return liga;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getCorUniforme() {
        return corUniforme;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setLiga(String liga) {
        this.liga = liga;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setCorUniforme(String corUniforme) {
        this.corUniforme = corUniforme;
    }
}

