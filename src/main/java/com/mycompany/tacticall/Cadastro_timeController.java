/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.tacticall;

import com.mycompany.dao.JogadorDAO;
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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
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
public class Cadastro_timeController extends Sidebar implements Initializable {

    List<Jogador> jogadores = new ArrayList<>();
    List<Jogador> jogadores_escalados = new ArrayList<>();
    @FXML
    VBox vbox_list_jogadores;
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
    @FXML
    TextField txt_pesquisa_jogador;
    @FXML
    TextField txt_pesquisa_jogador_e;

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

    private HBox HBox_Jogador(Jogador jogador) {
        HBox hBox = new HBox();
        hBox.getStyleClass().add("h_time_01");

        Circle avatarCircle = new Circle(48.0);
        avatarCircle.setFill(Color.web("#c6c6c6"));
        avatarCircle.setStroke(Color.web("#ebebeb"));
        avatarCircle.setStrokeWidth(3.0);

        VBox playerDetailsVBox = new VBox();
        playerDetailsVBox.setSpacing(5.0);

        Label playerNameLabel = new Label(jogador.getNome());
        playerNameLabel.getStyleClass().add("regular-text");
        playerNameLabel.setTextFill(Color.web("#5b5b5b"));
        playerNameLabel.setFont(new Font(22.0));

        Label playerDataLabel = new Label("Dados do jogador");
        playerDataLabel.getStyleClass().add("regular-text");
        playerDataLabel.setTextFill(Color.web("#808080"));
        playerDataLabel.setFont(new Font(18.0));
        playerDataLabel.setWrapText(true);

        playerDetailsVBox.getChildren().addAll(playerNameLabel, playerDataLabel);

        ImageView btnAddJogador = new ImageView(new Image(getClass().getResource("/Imagens/plus_icon.png").toExternalForm()));
        btnAddJogador.setFitHeight(32.0);
        btnAddJogador.setFitWidth(184.0);
        btnAddJogador.setOnMouseClicked(event -> Adicionar_Jogador(jogador));

        hBox.getChildren().addAll(avatarCircle, playerDetailsVBox, btnAddJogador);

        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: #a4a4a4;"); // Add the appropriate style

        hBox.setPadding(new Insets(0, 0, 8.0, 0)); // Adjust padding as needed

        return hBox;
    }

    private void criarHBoxes() {
        vbox_list_jogadores.getChildren().clear();

        for (Jogador jogador : jogadores) {
            if (!jogadorEscalado(jogador)) {
                HBox playerBox = HBox_Jogador(jogador);
                vbox_list_jogadores.getChildren().add(playerBox);
            }
        }
    }

    private boolean jogadorEscalado(Jogador j) {
        for (Jogador x : jogadores_escalados) {
            if (x.getId() == j.getId()) {
                return true;
            }
        }
        return false;
    }

    public void Adicionar_Jogador(Jogador jogador) {
        jogadores_escalados.add(jogador);
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
        for (Jogador j : jogadores_escalados) {
            RelacionamentoTimeProfissional rel = new RelacionamentoTimeProfissional(j.getId(), id_time);
            rel_dao.inserir(rel);
        }
        App.setRoot("times");
    }

    public void Pesquisar_Jogadores() throws TacticAllException {
        JogadorDAO j = new JogadorDAO();
        jogadores = j.listarPorNome(txt_pesquisa_jogador.getText());
        criarHBoxes();
    }

    public void Pesquisar_Jogadores_E() throws TacticAllException {
        pesquisaEscalados(txt_pesquisa_jogador_e.getText());
    }

    public void RemoverJogador(Jogador j) {

    }

    private void pesquisaEscalados(String pesquisa) {
        if (!pesquisa.isBlank()) {
            List<Jogador> j = new ArrayList<Jogador>();
            for (Jogador x : jogadores_escalados) {
                if (x.getNome().contains(pesquisa)) {
                    j.add(x);
                }
            }
            criarHBoxEscalados(j);
        } else {
            criarHBoxEscalados(jogadores_escalados);
        }

    }

    private void criarHBoxEscalados(List<Jogador> j) {

    }
    
    private HBox HBoxJogadorEscalado(Jogador jogador) {
        HBox hBox = new HBox();

        // Configuração da HBox
        hBox.setPrefHeight(98.0);
        hBox.setPrefWidth(212.0);
        hBox.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: #404040;");

        // Adicionando o círculo
        Circle circle = new Circle(34.0);
        circle.setFill(javafx.scene.paint.Color.web("#7c7c7c"));
        circle.setStroke(javafx.scene.paint.Color.web("#575757"));
        circle.setStrokeType(StrokeType.INSIDE);
        circle.setStrokeWidth(2.0);

        VBox vboxInterna = new VBox();
        VBox.setMargin(vboxInterna, new Insets(0, 0, 0, 20));

        // Adicionando labels na VBox interna
        Label labelNome = new Label(jogador.getNome());
        labelNome.getStyleClass().add("regular-text");
        labelNome.setStyle("-fx-text-fill: #d3d3d3;");
        labelNome.setFont(new Font(16.0));

        Label labelDados = new Label("Dados do jogador");
        labelDados.getStyleClass().add("regular-text");
        labelDados.setStyle("-fx-text-fill: #c7c7c7;");
        labelDados.setWrapText(true);
        VBox.setMargin(labelDados, new Insets(5.0, 0, 0, 0));

        vboxInterna.getChildren().addAll(labelNome, labelDados);

        // Adicionando a ImageView para remover o jogador
        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("@Imagens/x_icon.png")));
        imageView.setFitHeight(32.0);
        imageView.setFitWidth(184.0);
        imageView.setOnMouseClicked(event -> RemoverJogador(jogador));

        // Adicionando elementos à HBox
        hBox.getChildren().addAll(circle, vboxInterna, imageView);

        // Configuração do padding da HBox
        HBox.setMargin(hBox, new Insets(0, 0, 4.0, 0));

        return hBox;
    }
}
