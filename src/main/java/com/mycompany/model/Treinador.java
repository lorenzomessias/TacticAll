/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

/**
 *
 * @author Everymind
 */
public class Treinador {
    private int id;
    private int idProfissional;
    private String especialidade;
    private int qtdTitulos;
    private int qtdClubes;

    public Treinador(){}
    public Treinador(int id, int idProfissional, String especialidade, int qtdTitulos, int qtdClubes) {
        this.id = id;
        this.idProfissional = idProfissional;
        this.especialidade = especialidade;
        this.qtdTitulos = qtdTitulos;
        this.qtdClubes = qtdClubes;
    }
     public Treinador( int idProfissional, String especialidade, int qtdTitulos, int qtdClubes) {
        this.idProfissional = idProfissional;
        this.especialidade = especialidade;
        this.qtdTitulos = qtdTitulos;
        this.qtdClubes = qtdClubes;
    }

    public int getId() {
        return id;
    }

    public int getIdProfissional() {
        return idProfissional;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public int getQtdTitulos() {
        return qtdTitulos;
    }

    public int getQtdClubes() {
        return qtdClubes;
    }

    public void setIdProfissional(int idProfissional) {
        this.idProfissional = idProfissional;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public void setQtdTitulos(int qtdTitulos) {
        this.qtdTitulos = qtdTitulos;
    }

    public void setQtdClubes(int qtdClubes) {
        this.qtdClubes = qtdClubes;
    }
}

