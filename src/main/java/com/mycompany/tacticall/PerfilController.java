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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author moond
 */
public class PerfilController extends Header implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField txt_nome;
    @FXML
    private TextField txt_email;
    @FXML
    private TextField txt_senha;
    @FXML
    private DatePicker dt_nasc;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UsuarioDAO dao = new UsuarioDAO();
        try {
            Usuario usu = dao.listarPorEmail(Sessao.getInstancia().getEmail());
            txt_nome.setText(usu.getNome());
            txt_email.setText(usu.getEmail());
            txt_senha.setText(usu.getSenha());
            dt_nasc.setValue(usu.getDataDeNascimento());
        } catch (TacticAllException ex) {
            Logger.getLogger(PerfilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void ConcluirEdit() throws TacticAllException, IOException {
        UsuarioDAO dao = new UsuarioDAO();
        Usuario usu = dao.listarPorEmail(Sessao.getInstancia().getEmail());
        usu.setNome(txt_nome.getText());
        usu.setEmail(txt_email.getText());
        usu.setSenha(txt_senha.getText());
        usu.setDataDeNascimento(dt_nasc.getValue());
        dao.alterar(usu);
        App.setRoot("home");
    }
    
        public void DesativarConta() throws IOException, TacticAllException
    {
        UsuarioDAO dao = new UsuarioDAO();
        Usuario usu = dao.listarPorEmail(Sessao.getInstancia().getEmail());
        dao.desativar(usu);
        App.setRoot("primary");
    }
}
