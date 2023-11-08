/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tacticall;

/**
 *
 * @author moond
 */
 public final class Sessao {

    private static Sessao instancia;

    private String email;
    private String privilegios;
    private String nome;

    private Sessao(String email, String privilegios, String nome) {
        this.email = email;
        this.privilegios = privilegios;
        this.nome = nome;
    }

    public static void setInstancia(String email, String privilegios, String nome) {
        instancia = new Sessao(email, privilegios, nome);
    }
    
    public static Sessao getInstancia()
    {
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
