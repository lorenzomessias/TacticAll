/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

import java.io.Serializable;
import java.time.LocalDate;

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
   public Jogador(int id, String nome, LocalDate dataNascimento, String nacionalidade,
                   int notaGeral, String posicao, int idProfissional, String imagem) {
        this.id = id;
        this.setNome(nome);
        this.setDataDeNascimento(dataNascimento);
        this.setNacionalidade(nacionalidade);
        this.setNotaGeral(notaGeral); 
        this.posicao = posicao;
        this.idProfissional = idProfissional;
        this.setImagem(imagem);
    }

    public Jogador( String posicao, int idProfissional) {
        this.posicao = posicao;
        this.idProfissional = idProfissional;
    }

    public Jogador(int id, String nome, LocalDate dataNascimento, String nacionalidade,
                   int notaGeral, String posicao, int idProfissional, String imagem) {
        this.id = id;
        this.setNome(nome);
        this.setDataDeNascimento(dataNascimento);
        this.setNacionalidade(nacionalidade);
        this.setNotaGeral(notaGeral); 
        this.posicao = posicao;
        this.idProfissional = idProfissional;
        this.setImagem(imagem);
    }
    
    public Jogador() {
    }

    @Override
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
