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
import java.time.Month;
import java.util.Date;

public class Profissional {
    private int id;
    private String nome;
    private LocalDate dataDeNascimento;
    private String nacionalidade;
    private double notaGeral;
    private String imagem;
    public Profissional(){}
    public Profissional(int id, String nome, LocalDate dataDeNascimento, String nacionalidade, double notaGeral, String imagem) {
        this.id = id;
        this.nome = nome;
        this.dataDeNascimento = dataDeNascimento;
        this.nacionalidade = nacionalidade;
        this.notaGeral = notaGeral;
        this.imagem = imagem;
    }
    public Profissional( String nome, LocalDate dataDeNascimento, String nacionalidade, double notaGeral,String imagem) {
        this.nome = nome;
        this.dataDeNascimento = dataDeNascimento;
        this.nacionalidade = nacionalidade;
        this.notaGeral = notaGeral;
        this.imagem = imagem;

    }
     public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
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
            this.dataDeNascimento = LocalDate.of(1920, Month.JANUARY, 01);
    }

    public void setNacionalidade(String nacionalidade) {
         if(nacionalidade.equals("Poland")){
            this.nacionalidade = "Polônia";
        }else if(nacionalidade.equals("Germany")){
            this.nacionalidade = "Alemanha";
        }else if(nacionalidade.equals("Spain")){
            this.nacionalidade = "Espanha";
        }else if(nacionalidade.equals("Croatia")){
            this.nacionalidade = "Croacia";
        }else if(nacionalidade.equals("Belgium")){
            this.nacionalidade = "Belgica";
        }else if(nacionalidade.equals("France")){
            this.nacionalidade = "França";
        }else if(nacionalidade.equals("Brazil")){
            this.nacionalidade = "Brasil";
        }else if(nacionalidade.equals("Paraguay")){
            this.nacionalidade = "Paraguai";
        }else if(nacionalidade.equals("England")){
            this.nacionalidade = "Inglaterra";
        }else{
            this.nacionalidade = nacionalidade;
        }
    }

    public void setNotaGeral(double notaGeral) {
        this.notaGeral = notaGeral;
    }
}