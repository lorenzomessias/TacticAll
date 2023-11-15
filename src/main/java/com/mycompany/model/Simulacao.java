/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

/**
 *
 * @author Everymind
 */
import java.io.Serializable;
import java.util.Date;

public class Simulacao  implements Serializable{
    private int id;
    private int idEsquemaOfensivoMandante;
    private int idEsquemaOfensivoVisitante;
    private int idEsquemaDefensivoMandante;
    private int idEsquemaDefensivoVisitante;
    private int idUsuario;
    private int golsTimeVisitante;
    private int golsTimeMandante;
    private Date dataSimulacao;

    public Simulacao(){}
        public Simulacao(int id, int idEsquemaOfensivoMandante, int idEsquemaOfensivoVisitante,
                     int idEsquemaDefensivoMandante, int idEsquemaDefensivoVisitante,
                     int idUsuario, int golsTimeVisitante, int golsTimeMandante, Date dataSimulacao) {
        this.id = id;
        this.idEsquemaOfensivoMandante = idEsquemaOfensivoMandante;
        this.idEsquemaOfensivoVisitante = idEsquemaOfensivoVisitante;
        this.idEsquemaDefensivoMandante = idEsquemaDefensivoMandante;
        this.idEsquemaDefensivoVisitante = idEsquemaDefensivoVisitante;
        this.idUsuario = idUsuario;
        this.golsTimeVisitante = golsTimeVisitante;
        this.golsTimeMandante = golsTimeMandante;
        this.dataSimulacao = dataSimulacao;
    }
    public Simulacao(int idEsquemaOfensivoMandante, int idEsquemaOfensivoVisitante,
                     int idEsquemaDefensivoMandante, int idEsquemaDefensivoVisitante,
                     int idUsuario, int golsTimeVisitante, int golsTimeMandante, Date dataSimulacao) {
        this.idEsquemaOfensivoMandante = idEsquemaOfensivoMandante;
        this.idEsquemaOfensivoVisitante = idEsquemaOfensivoVisitante;
        this.idEsquemaDefensivoMandante = idEsquemaDefensivoMandante;
        this.idEsquemaDefensivoVisitante = idEsquemaDefensivoVisitante;
        this.idUsuario = idUsuario;
        this.golsTimeVisitante = golsTimeVisitante;
        this.golsTimeMandante = golsTimeMandante;
        this.dataSimulacao = dataSimulacao;
    }

    public int getId() {
        return id;
    }

    public int getIdEsquemaOfensivoMandante() {
        return idEsquemaOfensivoMandante;
    }

    public int getIdEsquemaOfensivoVisitante() {
        return idEsquemaOfensivoVisitante;
    }

    public int getIdEsquemaDefensivoMandante() {
        return idEsquemaDefensivoMandante;
    }

    public int getIdEsquemaDefensivoVisitante() {
        return idEsquemaDefensivoVisitante;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public int getGolsTimeVisitante() {
        return golsTimeVisitante;
    }

    public int getGolsTimeMandante() {
        return golsTimeMandante;
    }

    public Date getDataSimulacao() {
        return dataSimulacao;
    }

    public void setIdEsquemaOfensivoMandante(int idEsquemaOfensivoMandante) {
        this.idEsquemaOfensivoMandante = idEsquemaOfensivoMandante;
    }

    public void setIdEsquemaOfensivoVisitante(int idEsquemaOfensivoVisitante) {
        this.idEsquemaOfensivoVisitante = idEsquemaOfensivoVisitante;
    }

    public void setIdEsquemaDefensivoMandante(int idEsquemaDefensivoMandante) {
        this.idEsquemaDefensivoMandante = idEsquemaDefensivoMandante;
    }

    public void setIdEsquemaDefensivoVisitante(int idEsquemaDefensivoVisitante) {
        this.idEsquemaDefensivoVisitante = idEsquemaDefensivoVisitante;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setGolsTimeVisitante(int golsTimeVisitante) {
        this.golsTimeVisitante = golsTimeVisitante;
    }

    public void setGolsTimeMandante(int golsTimeMandante) {
        this.golsTimeMandante = golsTimeMandante;
    }

    public void setDataSimulacao(Date dataSimulacao) {
        this.dataSimulacao = dataSimulacao;
    }
}

