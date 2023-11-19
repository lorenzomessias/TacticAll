/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.tacticall;

import com.mycompany.dao.RelacionamentoTimeProfissionalDAO;
import com.mycompany.dao.TimeDAO;
import com.mycompany.dao.UsuarioDAO;
import com.mycompany.exception.TacticAllException;
import com.mycompany.model.Jogador;
import com.mycompany.model.RelacionamentoTimeProfissional;
import com.mycompany.model.Time;
import com.mycompany.model.Usuario;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author moond
 */
public class Cadastro_timeController extends Sidebar implements Initializable {
    
 List<Jogador> jogadores = new ArrayList<>();

    @FXML
    TextField txt_nome_t;
    @FXML
    TextField txt_sigla_t;
    @FXML
    TextField txt_pais_t;
    @FXML
    TextField txt_liga_t;
    @FXML
    ColorPicker cor_t;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Espacamento_Botoes();
        try {
            VerificaLogin();
        } catch (IOException ex) {
            Logger.getLogger(Cadastro_timeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Adicionar_Jogador() {
        
    }
    
    public void SalvarTime() throws TacticAllException, IOException {
        UsuarioDAO user_dao = new UsuarioDAO();
        Usuario user = user_dao.listarPorEmail(Sessao.getInstancia().getEmail());
        Time novotime = new Time(txt_nome_t.getText(), txt_sigla_t.getText(), txt_pais_t.getText(), txt_liga_t.getText(), 
                         user.getId(), cor_t.getValue().toString());
        TimeDAO time_dao = new TimeDAO();
        time_dao.inserir(novotime);
        
        int id_time = time_dao.IdMaisRecente();
        
        RelacionamentoTimeProfissionalDAO rel_dao = new RelacionamentoTimeProfissionalDAO();
        for(Jogador j : jogadores)
        {
            RelacionamentoTimeProfissional rel = new RelacionamentoTimeProfissional(j.getId(), id_time);
            rel_dao.inserir(rel);
        }
        App.setRoot("times");
    }
}
