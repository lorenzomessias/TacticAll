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
    private int habilidade;
    private String posicao;
    private int velocidade;
    private int resistencia;
    private int tecnica;
    private int chute;
    private int passe;
    private int idProfissional;

    public Jogador(int id, int habilidade, String posicao, int velocidade, int resistencia, int tecnica,
            int chute, int passe, int idProfissional) {
        this.id = id;
        this.habilidade = habilidade;
        this.posicao = posicao;
        this.velocidade = velocidade;
        this.resistencia = resistencia;
        this.tecnica = tecnica;
        this.chute = chute;
        this.passe = passe;
        this.idProfissional = idProfissional;
    }

    public Jogador(int habilidade, String posicao, int velocidade, int resistencia, int tecnica, int chute, int passe, int idProfissional) {
        this.habilidade = habilidade;
        this.posicao = posicao;
        this.velocidade = velocidade;
        this.resistencia = resistencia;
        this.tecnica = tecnica;
        this.chute = chute;
        this.passe = passe;
        this.idProfissional = idProfissional;
    }

    public Jogador() {
    }

    public int getId() {
        return id;
    }

    public int getHabilidade() {
        return habilidade;
    }

    public void setHabilidade(int habilidade) {
        this.habilidade = habilidade;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public int getResistencia() {
        return resistencia;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public int getTecnica() {
        return tecnica;
    }

    public void setTecnica(int tecnica) {
        this.tecnica = tecnica;
    }

    public int getChute() {
        return chute;
    }

    public void setChute(int chute) {
        this.chute = chute;
    }

    public int getPasse() {
        return passe;
    }

    public void setPasse(int passe) {
        this.passe = passe;
    }

    public int getIdProfissional() {
        return idProfissional;
    }

    public void setIdProfissional(int idProfissional) {
        this.idProfissional = idProfissional;
    }
}
