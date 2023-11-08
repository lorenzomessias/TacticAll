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
import java.time.Period;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
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
    private PasswordField txt_senha;

    @FXML
    private DatePicker dt_nasc;

    public void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    public boolean validarCampos() {
        if (txt_nome.getText().isEmpty() || txt_email.getText().isEmpty() || txt_senha.getText().isEmpty() || dt_nasc.getValue() == null) {
            Alert alerta = new Alert(AlertType.ERROR, "Por favor, preencha todos os campos.");
            alerta.setTitle("Campos em branco");
            alerta.setHeaderText("Campos obrigatórios não preenchidos.");
            alerta.showAndWait();
            return false;
        }

        String email = txt_email.getText();

        if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            Alert alerta = new Alert(AlertType.ERROR, "Por favor, insira um email válido.");
            alerta.setTitle("Email inválido");
            alerta.setHeaderText("O email não segue a estrutura correta.");
            alerta.showAndWait();
            return false;
        }

        LocalDate dataNascimento = dt_nasc.getValue();
        LocalDate dataAtual = LocalDate.now();
        int idade = Period.between(dataNascimento, dataAtual).getYears();

        if (idade < 13) {
            Alert alerta = new Alert(AlertType.ERROR, "Você deve ter pelo menos 13 anos para se cadastrar.");
            alerta.setTitle("Idade insuficiente");
            alerta.setHeaderText("Idade mínima não atingida.");
            alerta.showAndWait();
            return false;
        }
        if(verificaUsuarioExistente(txt_email.getText())){
            Alert alerta = new Alert(AlertType.ERROR, "Você deve informar um e-mail não cadastrado");
            alerta.setTitle("Usuário Existente");
            alerta.setHeaderText("Usuário Existente");
            alerta.showAndWait();
            return false;
        }
        return true;
    }
    private Boolean verificaUsuarioExistente(String email){
        try{
            UsuarioDAO u = new UsuarioDAO();
            Usuario user = u.listarPorEmail(email);
            return user.getEmail() != null ? true : false;
        } catch(TacticAllException ex){
                ex.printStackTrace();
                Alert alerta = new Alert(AlertType.ERROR, "Erro ao concluir o cadastro: " + ex.getMessage());
                alerta.setTitle("Erro ao concluir o cadastro");
                alerta.setHeaderText("Erro de cadastro");
                alerta.showAndWait();
        }
        return false;
    }
    public void ConcluirCadastro() throws IOException {
        if (validarCampos()) {
            try {
                Usuario usu = new Usuario(txt_nome.getText(), txt_email.getText(), dt_nasc.getValue(), txt_senha.getText(), 1);
                UsuarioDAO dao = new UsuarioDAO();
                dao.inserir(usu);

                // Cadastro bem-sucedido, exibir mensagem de sucesso
                Alert sucesso = new Alert(AlertType.INFORMATION);
                sucesso.setTitle("Cadastro Concluído");
                sucesso.setHeaderText("Seu cadastro foi concluído com sucesso.");
                sucesso.showAndWait();

                App.setRoot("primary");
            } catch (TacticAllException ex) {
                // Lide com a exceção TacticAllException aqui, por exemplo, exiba uma mensagem de erro.
                ex.printStackTrace();
                // Ou exiba uma mensagem de erro ao usuário
                Alert alerta = new Alert(AlertType.ERROR, "Erro ao concluir o cadastro: " + ex.getMessage());
                alerta.setTitle("Erro ao concluir o cadastro");
                alerta.setHeaderText("Erro de cadastro");
                alerta.showAndWait();
            }
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
