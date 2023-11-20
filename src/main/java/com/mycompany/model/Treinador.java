/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

import java.io.Serializable;

/**
 *
 * @author Everymind
 */
public class Treinador extends Profissional implements Serializable {
    private int id;
    private int idProfissional;
    private String especialidade;

    public Treinador(){}
    public Treinador(int id, int idProfissional, String especialidade) {
        this.id = id;
        this.idProfissional = idProfissional;
        this.especialidade = especialidade;

    }
     public Treinador( int idProfissional, String especialidade) {
        this.idProfissional = idProfissional;
        this.especialidade = especialidade;

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


    public void setIdProfissional(int idProfissional) {
        this.idProfissional = idProfissional;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

}

