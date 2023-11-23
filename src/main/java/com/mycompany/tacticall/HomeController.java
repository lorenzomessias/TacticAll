/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.tacticall;

import com.mycompany.dao.EsquemaTaticoDAO;
import com.mycompany.dao.SimulacaoDAO;
import com.mycompany.dao.TimeDAO;
import com.mycompany.dao.UsuarioDAO;
import com.mycompany.exception.TacticAllException;
import com.mycompany.model.EsquemaTatico;
import com.mycompany.model.Simulacao;
import com.mycompany.model.Time;
import com.mycompany.model.Usuario;
import com.mycompany.service.SoccerService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
public class HomeController extends Sidebar implements Initializable {

    @FXML
    Label txt_user;
    @FXML
    VBox vbox_maisrecente;

    private Simulacao simulacaoRecente = new Simulacao();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            VerificaLogin();
            Pesquisar_Simulacoes();
        } catch (IOException | TacticAllException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        txt_user.setText(Sessao.getInstancia().getNome());
    }

    public void Ir_Criar_Time() throws IOException {
        App.setRoot("cadastro_time");
    }

    public void Ir_Criar_EsquemaTatico() throws IOException {
        App.setRoot("cadastro_esquema_tatico");
    }

    public void requisitarJogadores() {
        SoccerService.iniciaServico();
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Requisição de Profissionais");
        alert.setHeaderText(null);
        alert.setContentText("A requisição de profissionais foi realizada com sucesso!");

        // Exibindo o alerta
        alert.showAndWait();
    }

    public void Pesquisar_Simulacoes() throws TacticAllException {
        SimulacaoDAO sDao = new SimulacaoDAO();
        simulacaoRecente = sDao.listarMaisRecente(Sessao.getInstancia().getId());
        criarHBoxes();
    }

        private void criarHBoxes() {
        Platform.runLater(() -> {
            vbox_maisrecente.getChildren().clear();
            if (simulacaoRecente == null || simulacaoRecente.getId() < 1) {
                vbox_maisrecente.getChildren().add(hBoxVazia());
            } else {
                HBox hBox = hBoxSimulacao(simulacaoRecente);
                vbox_maisrecente.getChildren().add(hBox);
            }
        });
    }
        
    private HBox hBoxVazia() {
        HBox hboxV = new HBox();
        Label label = new Label("Você ainda não realizou nenhuma simulação.");
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
