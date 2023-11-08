/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.tacticall;

import java.io.IOException;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

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
    
        public void VerificaLogin() throws IOException {
        if (Sessao.getInstancia() == null) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Acesso Restrito");
            alerta.setHeaderText("Você deve estar logado para acessar esta página.");
            alerta.setContentText("Faça o login ou crie uma conta para acessar o conteúdo.");

            ButtonType loginButton = new ButtonType("Fazer Login");
            ButtonType criarContaButton = new ButtonType("Criar Conta");

            alerta.getButtonTypes().setAll(loginButton, criarContaButton);

            Optional<ButtonType> resultado = alerta.showAndWait();
            if (resultado.isPresent() && resultado.get() == loginButton) {
                App.setRoot("primary");
            } else if (resultado.isPresent() && resultado.get() == criarContaButton) {
                App.setRoot("cadastro");
            }
        }
    }
}

