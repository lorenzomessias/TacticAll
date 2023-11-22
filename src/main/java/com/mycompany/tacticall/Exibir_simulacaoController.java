/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.tacticall;

import com.mycompany.dao.EsquemaTaticoDAO;
import com.mycompany.dao.TimeDAO;
import com.mycompany.exception.TacticAllException;
import com.mycompany.model.EsquemaTatico;
import com.mycompany.model.Simulacao;
import com.mycompany.model.Time;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author moond
 */
public class Exibir_simulacaoController extends Sidebar implements Initializable {

    private Simulacao simulacaoAtual;

    @FXML
    private Label NomeTime_M;

    @FXML
    private Circle CorTime_M;

    @FXML
    private Label FormacaoO_M;

    @FXML
    private Label NomeEsquemaO_M;

    @FXML
    private Label NomeEsquemaD_M;

    @FXML
    private Label FormacaoD_M;

    @FXML
    private Label GolTime_M;

    @FXML
    private Label NomeTime_V;

    @FXML
    private Circle CorTime_V;

    @FXML
    private Label FormacaoO_V;

    @FXML
    private Label NomeEsquemaD_V;

    @FXML
    private Label NomeEsquemaO_V;

    @FXML
    private Label FormacaoD_V;

    @FXML
    private Label GolTime_V;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            preencherCampos();
        } catch (TacticAllException ex) {
            Logger.getLogger(Exibir_simulacaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void preencherCampos() throws TacticAllException {
        simulacaoAtual = Sessao.getInstancia().getSimulacao();
        EsquemaTaticoDAO esDAO = new EsquemaTaticoDAO();
        EsquemaTatico ofensivo_M = esDAO.listarPorID(simulacaoAtual.getIdEsquemaOfensivoMandante());
        EsquemaTatico defensivo_M = esDAO.listarPorID(simulacaoAtual.getIdEsquemaDefensivoMandante());
        EsquemaTatico ofensivo_V = esDAO.listarPorID(simulacaoAtual.getIdEsquemaOfensivoVisitante());
        EsquemaTatico defensivo_V = esDAO.listarPorID(simulacaoAtual.getIdEsquemaDefensivoVisitante());

        TimeDAO tDAO = new TimeDAO();
        Time time_M = tDAO.listarPorID(ofensivo_M.getIdTime());
        Time time_V = tDAO.listarPorID(ofensivo_V.getIdTime());

        CorTime_M.setFill(Color.web(time_M.getCorUniforme()));
        CorTime_V.setFill(Color.web(time_V.getCorUniforme()));

        GolTime_M.setText(String.valueOf(simulacaoAtual.getGolsTimeMandante()));
        GolTime_V.setText(String.valueOf(simulacaoAtual.getGolsTimeVisitante()));

        NomeTime_M.setText(time_M.getNome());
        NomeTime_V.setText(time_V.getNome());
        
        NomeEsquemaO_M.setText(ofensivo_M.getNome());
        NomeEsquemaD_M.setText(defensivo_M.getNome());
        FormacaoO_M.setText(ofensivo_M.getFormacao());
        FormacaoD_M.setText(defensivo_M.getFormacao());
        
        NomeEsquemaO_V.setText(ofensivo_V.getNome());
        NomeEsquemaD_V.setText(defensivo_V.getNome());
        FormacaoO_V.setText(ofensivo_V.getFormacao());
        FormacaoD_V.setText(defensivo_V.getFormacao());
        
    }

}
