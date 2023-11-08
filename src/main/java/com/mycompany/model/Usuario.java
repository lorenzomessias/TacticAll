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
public class Usuario implements Serializable {
    
    private int id;
    private String nome;
    private String email;
    private LocalDate dataDeNascimento;
    private String senha;
    private int ativo;

    public Usuario() {
    }
    
    public Usuario(String nome, String email, LocalDate dataDeNascimento, String senha, int ativo) {
        this.nome = nome;
        this.email = email;
        this.dataDeNascimento = dataDeNascimento;
        this.senha = senha;
        this.ativo = ativo;
    }

    public Usuario(int id, String nome, String email, LocalDate dataDeNascimento, String senha, int ativo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dataDeNascimento = dataDeNascimento;
        this.senha = senha;
        this.ativo = ativo;
    }
    
    

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public int getAtivo() {
        return ativo;
    }
    
    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }
}
