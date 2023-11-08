/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.tacticall;

import com.mycompany.dao.UsuarioDAO;
import com.mycompany.exception.TacticAllException;
import com.mycompany.model.Usuario;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author moond
 */
public class PrimaryController implements Initializable {

    @FXML
    TextField txt_email;
    
    @FXML
    TextField txt_senha;
    
    public void FazerLogin() throws IOException, TacticAllException
    {
        UsuarioDAO dao = new UsuarioDAO();
        Usuario usu = dao.listarPorEmail(txt_email.getText());
        if(usu.getEmail() != null && usu.getSenha().equals(txt_senha.getText()))
        {
          Sessao.setInstancia(txt_email.getText(), "login", usu.getNome());
          App.setRoot("home");  
        }
        else
        {
            Alert alerta = new Alert(AlertType.ERROR, "Usuário ou senha inválidos.");
            alerta.setTitle("Erro ao fazer login");
            alerta.setHeaderText("Erro de identificação");
            alerta.showAndWait();
        }
    }
        public void switchToCadastro() throws IOException
    {
        App.setRoot("cadastro");
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
