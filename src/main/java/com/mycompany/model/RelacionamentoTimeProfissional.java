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
public class RelacionamentoTimeProfissional implements Serializable {

    private int id;
    private int idProfissional;
    private int idTime;

    public RelacionamentoTimeProfissional(int id, int idProfissional, int idTime) {
        this.id = id;
        this.idProfissional = idProfissional;
        this.idTime = idTime;
    }
    
    public RelacionamentoTimeProfissional(int idProfissional, int idTime) {
        this.idProfissional = idProfissional;
        this.idTime = idTime;
    }
    
    public RelacionamentoTimeProfissional() {

    }

    public int getId() {
        return id;
    }

    public int getIdProfissional() {
        return idProfissional;
    }

    public void setIdProfissional(int idProfissional) {
        this.idProfissional = idProfissional;
    }

    public int getIdTime() {
        return idTime;
    }

    public void setIdTime(int idTime) {
        this.idTime = idTime;
    }
}
