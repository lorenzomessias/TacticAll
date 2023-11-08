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
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author moond
 */
public class CadastroController implements Initializable {
    
    @FXML
    private TextField txt_nome;
    
   @FXML
   private TextField txt_email;
   
   @FXML
   private TextField txt_senha;
   
   @FXML
   private DatePicker dt_nasc;
    
        public void switchToPrimary() throws IOException
    {
        App.setRoot("primary");
    }
        public void ConcluirCadastro() throws IOException, TacticAllException
    {
            Usuario usu = new Usuario(txt_nome.getText(), txt_email.getText(), dt_nasc.getValue(), txt_senha.getText(), 1);
            UsuarioDAO dao = new UsuarioDAO();
            dao.inserir(usu);
        App.setRoot("primary");
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
