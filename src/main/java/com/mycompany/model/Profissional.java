/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

/**
 *
 * @author Everymind
 */
import java.time.LocalDate;
import java.util.Date;

public class Profissional {
    private int id;
    private String nome;
    private LocalDate dataDeNascimento;
    private String nacionalidade;
    private double notaGeral;
    public Profissional(){}
    public Profissional(int id, String nome, LocalDate dataDeNascimento, String nacionalidade, double notaGeral) {
        this.id = id;
        this.nome = nome;
        this.dataDeNascimento = dataDeNascimento;
        this.nacionalidade = nacionalidade;
        this.notaGeral = notaGeral;
    }
    public Profissional( String nome, LocalDate dataDeNascimento, String nacionalidade, double notaGeral) {
        this.nome = nome;
        this.dataDeNascimento = dataDeNascimento;
        this.nacionalidade = nacionalidade;
        this.notaGeral = notaGeral;
    }
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public double getNotaGeral() {
        return notaGeral;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public void setNotaGeral(double notaGeral) {
        this.notaGeral = notaGeral;
    }
}