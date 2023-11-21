/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.tacticall;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author moond
 */
public class TimesController extends Sidebar implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            VerificaLogin();
        } catch (IOException ex) {
            Logger.getLogger(TimesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void Ir_Criar_Time() throws IOException {
        App.setRoot("cadastro_time");
    }
    
    public void Pesquisar_Times() {
        
    }
    
    public void Editar_Time() {
        
    }
    
    public void Remover_Time() {
        
    }
}
