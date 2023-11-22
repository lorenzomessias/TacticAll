/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.tacticall;

import com.mycompany.dao.EsquemaTaticoDAO;
import com.mycompany.dao.SimulacaoDAO;
import com.mycompany.dao.TimeDAO;
import com.mycompany.exception.TacticAllException;
import com.mycompany.model.EsquemaTatico;
import com.mycompany.model.Simulacao;
import com.mycompany.model.Time;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author moond
 */
public class HistoricoController extends Sidebar implements Initializable {

    List<Simulacao> simulacoes = new ArrayList<>();
    @FXML
    TextField txt_pesquisa_simulacao;
    @FXML
    VBox vbox_lista_simulacoes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Pesquisar_Simulacoes();
        } catch (TacticAllException ex) {
            Logger.getLogger(HistoricoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Pesquisar_Simulacoes() throws TacticAllException {
        SimulacaoDAO sDao = new SimulacaoDAO();
        simulacoes = sDao.listarPorIdUsuario(Sessao.getInstancia().getId());
        criarHBoxes();
    }

    private void criarHBoxes() {
        Platform.runLater(() -> {
            vbox_lista_simulacoes.getChildren().clear();
            if (simulacoes.isEmpty()) {
                vbox_lista_simulacoes.getChildren().add(hBoxVazia());
            } else {
                for (Simulacao simulacao : simulacoes) {
                    HBox hBox = hBoxSimulacao(simulacao);
                    vbox_lista_simulacoes.getChildren().add(hBox);
                }
            }
        });
    }

    private HBox hBoxVazia() {
        HBox hboxV = new HBox();
        Label label = new Label("Não foram encontradas simulações com essas especificações.");
        label.getStyleClass().add("regular-text");
        label.getStylesheets().add("@../../../styles/styles.css");
        label.setTextFill(javafx.scene.paint.Color.web("#808080"));
        label.setWrapText(true);
        label.setFont(new javafx.scene.text.Font(18.0));

        // Adiciona o Label à HBox
        hboxV.getChildren().add(label);

        // Configuração de estilos e margens
        hboxV.setAlignment(javafx.geometry.Pos.CENTER);
        hboxV.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: a4a4a4;");
        hboxV.setPadding(new javafx.geometry.Insets(0, 0, 20, 0));
        return hboxV;
    }

    private HBox hBoxSimulacao(Simulacao simulacaoAtual) {
        HBox hboxSimulacao = new HBox();

        // VBox Mandante
        VBox vboxMandante = new VBox();
        vboxMandante.setAlignment(Pos.CENTER);

        Label nomeTimeMandante = new Label();
        nomeTimeMandante.getStyleClass().add("regular-text");
        nomeTimeMandante.getStylesheets().add("@../../../styles/styles.css");
        nomeTimeMandante.setTextFill(Color.web("#656565"));
        nomeTimeMandante.setText("Nome do Time Mandante");
        nomeTimeMandante.setWrapText(true);
        nomeTimeMandante.setFont(new Font(22.0));
        nomeTimeMandante.setPadding(new Insets(0, 0, 10.0, 10.0));

        HBox circleHBoxMandante = new HBox();
        circleHBoxMandante.setAlignment(Pos.CENTER);

        Circle corTimeMandante = new Circle(42.0, Color.web("#c6c6c6"));
        corTimeMandante.setStroke(Color.web("#ebebeb"));
        corTimeMandante.setStrokeType(StrokeType.INSIDE);
        corTimeMandante.setStrokeWidth(3.0);

        circleHBoxMandante.getChildren().add(corTimeMandante);

        vboxMandante.getChildren().addAll(nomeTimeMandante, circleHBoxMandante);

        // HBox Gol
        HBox hboxGol = new HBox();
        hboxGol.setAlignment(Pos.CENTER);

        Label golTimeMandante = new Label("0");
        golTimeMandante.getStyleClass().add("regular-text");
        golTimeMandante.getStylesheets().add("@../../../styles/styles.css");
        golTimeMandante.setTextFill(Color.web("#656565"));
        golTimeMandante.setWrapText(true);
        golTimeMandante.setFont(new Font(38.0));
        golTimeMandante.setPadding(new Insets(0, 0, 0, 10.0));

        Label separadorGol = new Label("-");
        separadorGol.getStyleClass().add("regular-text");
        separadorGol.getStylesheets().add("@../../../styles/styles.css");
        separadorGol.setTextFill(Color.web("#656565"));
        separadorGol.setWrapText(true);
        separadorGol.setFont(new Font(38.0));
        separadorGol.setPadding(new Insets(0, 0, 0, 10.0));

        Label golTimeVisitante = new Label("0");
        golTimeVisitante.getStyleClass().add("regular-text");
        golTimeVisitante.getStylesheets().add("@../../../styles/styles.css");
        golTimeVisitante.setTextFill(Color.web("#656565"));
        golTimeVisitante.setWrapText(true);
        golTimeVisitante.setFont(new Font(38.0));
        golTimeVisitante.setPadding(new Insets(0, 0, 0, 10.0));
        hboxGol.getChildren().addAll(golTimeMandante, separadorGol, golTimeVisitante);
        hboxGol.setPadding(new Insets(40.0, 0, 0, 0));
        VBox.setVgrow(hboxGol, Priority.ALWAYS);

        // VBox Visitante
        VBox vboxVisitante = new VBox();
        vboxVisitante.setAlignment(Pos.CENTER);

        Label nomeTimeVisitante = new Label();
        nomeTimeVisitante.getStyleClass().add("regular-text");
        nomeTimeVisitante.getStylesheets().add("@../../../styles/styles.css");
        nomeTimeVisitante.setTextFill(Color.web("#656565"));
        nomeTimeVisitante.setText("Nome do Time Visitante");
        nomeTimeVisitante.setWrapText(true);
        nomeTimeVisitante.setFont(new Font(22.0));
        nomeTimeVisitante.setPadding(new Insets(0, 0, 10.0, 10.0));

        HBox circleHBoxVisitante = new HBox();
        circleHBoxVisitante.setAlignment(Pos.CENTER);

        Circle corTimeVisitante = new Circle(42.0, Color.web("#c6c6c6"));
        corTimeVisitante.setStroke(Color.web("#ebebeb"));
        corTimeVisitante.setStrokeType(StrokeType.INSIDE);
        corTimeVisitante.setStrokeWidth(3.0);

        try {
            EsquemaTaticoDAO esDAO = new EsquemaTaticoDAO();
            EsquemaTatico ofensivo_M = esDAO.listarPorID(simulacaoAtual.getIdEsquemaOfensivoMandante());
            EsquemaTatico defensivo_M = esDAO.listarPorID(simulacaoAtual.getIdEsquemaDefensivoMandante());
            EsquemaTatico ofensivo_V = esDAO.listarPorID(simulacaoAtual.getIdEsquemaOfensivoVisitante());
            EsquemaTatico defensivo_V = esDAO.listarPorID(simulacaoAtual.getIdEsquemaDefensivoVisitante());

            TimeDAO tDAO = new TimeDAO();
            Time time_M = tDAO.listarPorID(ofensivo_M.getIdTime());
            Time time_V = tDAO.listarPorID(ofensivo_V.getIdTime());

            // Preencher os campos do Mandante
            nomeTimeMandante.setText(time_M.getNome());
            corTimeMandante.setFill(Color.web(time_M.getCorUniforme()));
            golTimeMandante.setText(String.valueOf(simulacaoAtual.getGolsTimeMandante()));

            // Preencher os campos do Visitante
            nomeTimeVisitante.setText(time_V.getNome());
            corTimeVisitante.setFill(Color.web(time_V.getCorUniforme()));
            golTimeVisitante.setText(String.valueOf(simulacaoAtual.getGolsTimeVisitante()));
        } catch (TacticAllException e) {
            // Trate a exceção conforme necessário
            e.printStackTrace();
        }

        circleHBoxVisitante.getChildren().add(corTimeVisitante);

        vboxVisitante.getChildren().addAll(nomeTimeVisitante, circleHBoxVisitante);

        hboxSimulacao.getChildren().addAll(vboxMandante, hboxGol, vboxVisitante);

        hboxSimulacao.setAlignment(Pos.CENTER);
        hboxSimulacao.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: #a4a4a4;");
        hboxSimulacao.setPadding(new Insets(0, 0, 12.0, 0));
        VBox.setMargin(hboxSimulacao, new Insets(10.0, 0, 0, 0));

        return hboxSimulacao;
    }

}
