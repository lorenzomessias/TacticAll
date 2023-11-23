/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tacticall;

import com.mycompany.model.EsquemaTatico;
import com.mycompany.model.Simulacao;
import com.mycompany.model.Time;

/**
 *
 * @author moond
 */
public final class Sessao {

    private static Sessao instancia;

    private String email;
    private String privilegios;
    private String nome;

    public Simulacao getSimulacao() {
        return simulacao;
    }

    public void setSimulacao(Simulacao simulacao) {
        this.simulacao = simulacao;
    }
    
    private Simulacao simulacao;

    public Sessao(String email, String privilegios, String nome, int id) {
        this.email = email;
        this.privilegios = privilegios;
        this.nome = nome;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    private int id;
    private Time timeEditando;
    private EsquemaTatico esquemaTaticoEditando;

    public Time getTimeEditando() {
        return timeEditando;
    }

    public void setTimeEditando(Time timeEditando) {
        this.timeEditando = timeEditando;
    }

    public EsquemaTatico getEsquemaTaticoEditando() {
        return esquemaTaticoEditando;
    }

    public void setEsquemaTaticoEditando(EsquemaTatico esquemaTaticoEditando) {
        this.esquemaTaticoEditando = esquemaTaticoEditando;
    }

    private Sessao(String email, String privilegios, String nome) {
        this.email = email;
        this.privilegios = privilegios;
        this.nome = nome;
    }

    public static void setInstancia(String email, String privilegios, String nome) {
        instancia = new Sessao(email, privilegios, nome);
    }
    
        public static void setInstancia(String email, String privilegios, String nome, int id) {
        instancia = new Sessao(email, privilegios, nome, id);
    }
    public static Sessao getInstancia() {
        return instancia;
    }

    public String getEmail() {
        return email;
    }

    public String getPrivilegios() {
        return privilegios;
    }

    public String getNome() {
        return nome;
    }

    public static void LimparSessao() {
        instancia = null;
    }
}
