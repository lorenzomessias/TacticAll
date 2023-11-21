/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.tacticall;

import java.io.IOException;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public abstract class Sidebar {

    @FXML
    private Button btn_ger_times;

    @FXML
    private Button btn_ger_esquemas;

    @FXML
    private Button btn_simulacao;

    @FXML
    private Button btn_historico;

    @FXML
    private Button btn_editar_perfil;

    private void setLetterSpacing(Button button) {
        // Add a custom style to the button
        button.setStyle("-fx-letter-spacing: 0.4em;"); // Adjust the letter spacing as needed
    }

    public void Espacamento_Botoes() {
        setLetterSpacing(btn_ger_times);
        setLetterSpacing(btn_ger_esquemas);
        setLetterSpacing(btn_simulacao);
        setLetterSpacing(btn_historico);
        setLetterSpacing(btn_editar_perfil);
    }

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

    public void Ir_Times() throws IOException {
        App.setRoot("times");
    }
    

    public void Ir_Esquemas_Taticos() throws IOException {
        App.setRoot("esquemas_taticos");
    }

    public void Ir_Simulacao() throws IOException {
        App.setRoot("simulacao");
    }

    public void Ir_Historico() throws IOException {
        App.setRoot("historico");
    }
}
