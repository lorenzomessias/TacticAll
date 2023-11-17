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
public class Cadastro_timeController extends Sidebar implements Initializable {

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
    
}
