/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

import java.io.Serializable;

/**
 *
 * @author CS
 */
public class Jogador extends Profissional implements Serializable{

    private int id;
    private String posicao;
    private int idProfissional;

    public Jogador(int id, String posicao, int idProfissional) {
        this.id = id;
        this.posicao = posicao;
        this.idProfissional = idProfissional;
    }

    public Jogador( String posicao, int idProfissional) {
        this.posicao = posicao;
        this.idProfissional = idProfissional;
    }

    public Jogador() {
    }

    public int getId() {
        return id;
    }

   

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public int getIdProfissional() {
        return idProfissional;
    }

    public void setIdProfissional(int idProfissional) {
        this.idProfissional = idProfissional;
    }
}
