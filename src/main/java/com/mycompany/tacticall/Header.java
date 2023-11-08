/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.tacticall;

import java.io.IOException;

public abstract class Header {
    public void EditarPerfil() throws IOException {
        if (Sessao.getInstancia() == null) {
            App.setRoot("primary");
        } else {
            App.setRoot("perfil");
        }
    }

    public void LogOff() throws IOException {
        Sessao.LimparSessao();
        App.setRoot("primary");
    }
}

