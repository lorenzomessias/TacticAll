/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

/**
 *
 * @author Everymind
 */
public class RelacionamentoJogadorEsquema {
    private int id;
    private int idJogador;
    private int idEsquema;
    private String posicao;  // Adicionando o campo Posicao

    // Construtores, getters e setters
    public RelacionamentoJogadorEsquema(){}
    public RelacionamentoJogadorEsquema(int id, int idJogador, int idEsquema, String posicao) {
        this.id = id;
        this.idJogador = idJogador;
        this.idEsquema = idEsquema;
        this.posicao = posicao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(int idJogador) {
        this.idJogador = idJogador;
    }

    public int getIdEsquema() {
        return idEsquema;
    }

    public void setIdEsquema(int idEsquema) {
        this.idEsquema = idEsquema;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }
}

