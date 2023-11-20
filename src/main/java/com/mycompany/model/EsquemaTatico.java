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
public class EsquemaTatico implements Serializable {

    private int id;
    private int idTime;
    private String tipo;
    private String nome;
    private String formacao;
    private String taticaEspecifica;

    public EsquemaTatico(int id, int idTime, String tipo, String nome, String formacao, String taticaEspecifica) {
        this.id = id;
        this.idTime = idTime;
        this.tipo = tipo;
        this.nome = nome;
        this.formacao = formacao;
        this.taticaEspecifica = taticaEspecifica;
    }
    
    public EsquemaTatico(int idTime, String tipo, String nome, String formacao, String taticaEspecifica) {
        this.idTime = idTime;
        this.tipo = tipo;
        this.nome = nome;
        this.formacao = formacao;
        this.taticaEspecifica = taticaEspecifica;
    }
    
    public EsquemaTatico() {

    }

    public int getId() {
        return id;
    }

    public int getIdTime() {
        return idTime;
    }

    public void setIdTime(int idTime) {
        this.idTime = idTime;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }

    public String getTaticaEspecifica() {
        return taticaEspecifica;
    }

    public void setTaticaEspecifica(String taticaEspecifica) {
        this.taticaEspecifica = taticaEspecifica;
    }
}
