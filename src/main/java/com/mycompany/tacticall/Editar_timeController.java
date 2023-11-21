/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.tacticall;

import com.mycompany.dao.JogadorDAO;
import com.mycompany.dao.RelacionamentoTimeProfissionalDAO;
import com.mycompany.dao.TimeDAO;
import com.mycompany.dao.TreinadorDAO;
import com.mycompany.dao.UsuarioDAO;
import com.mycompany.exception.TacticAllException;
import com.mycompany.model.Jogador;
import com.mycompany.model.RelacionamentoTimeProfissional;
import com.mycompany.model.Time;
import com.mycompany.model.Treinador;
import com.mycompany.model.Usuario;
import java.io.IOException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * FXML Controller class
 *
 * @author moond
 */
public class Editar_timeController extends Sidebar implements Initializable {

    private Time time;
    List<Jogador> jogadores = new ArrayList<>();
    List<Jogador> jogadores_escalados = new ArrayList<>();
    List<Treinador> tecnicos = new ArrayList<>();
    Treinador tecnicoSelecionado = null;

    @FXML
    VBox vbox_list_jogadores;
    @FXML
    VBox vbox_list_tecnicos;
    @FXML
    VBox vbox_tecnicoSelecionado;
    @FXML
    VBox vbox_jogadores_e;
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
    @FXML
    TextField txt_pesquisa_tecnico;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            VerificaLogin();
            preencherCampos(time);
            Pesquisar_Jogadores();
            Pesquisar_Jogadores_E();
            pesquisarTecnico();
        } catch (IOException | TacticAllException ex) {
            Logger.getLogger(Cadastro_timeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setTime(Time t) throws TacticAllException
    {
        this.time = t;
        preencherCampos(time);
        Pesquisar_Jogadores();
        Pesquisar_Jogadores_E();
        pesquisarTecnico();
        preencherTecnicoSelecionado();
    }
    
    private void preencherCampos(Time t) throws TacticAllException {
        if(time != null)
        {
        txt_nome_t.setText(t.getNome());
        txt_sigla_t.setText(t.getSigla());
        txt_liga_t.setText(t.getLiga());
        txt_pais_t.setText(t.getPais());
        cor_t.setValue(Color.valueOf(t.getCorUniforme()));
        JogadorDAO jDAO = new JogadorDAO();
        jogadores_escalados = jDAO.listarPorTime(t.getId());
        TreinadorDAO tDAO = new TreinadorDAO();
        tecnicoSelecionado = tDAO.listarPorTime(t.getId());
        }
    }

    private HBox HBox_Jogador(Jogador jogador) {
        HBox hBox = new HBox();
        hBox.setPrefHeight(200.0);
        hBox.setPrefWidth(100.0);
        hBox.setStyle("-fx-background-color: #e3e3e3;");

        // Configuração para o HBox com id "h_time_01"
        hBox.setId("h_time_01");
        hBox.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        hBox.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: a4a4a4;");
        hBox.setPadding(new Insets(10.0, 0, 8.0, 0));

        // Criar um círculo como moldura
        Circle circle = new Circle(40.0, Color.web("#c6c6c6"));
        circle.setStroke(Color.web("#ebebeb"));
        circle.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);
        circle.setStrokeWidth(3.0);

        // Criar um ImageView para a imagem do jogador a partir de um URL
        if (!jogador.getImagem().equals("null")) {
            String imageUrl = jogador.getImagem(); // Substitua pelo URL real
            Image image = new Image(imageUrl);
            circle.setFill(new ImagePattern(image));
        }

        // Criar a VBox interna
        VBox vBox = new VBox();
        vBox.setAlignment(javafx.geometry.Pos.TOP_LEFT);
        vBox.setPadding(new Insets(10.0, 0, 0, 20.0));

        // Criar e configurar os Labels
        Label lbNome = new Label(jogador.getNome());
        lbNome.setId("lb_jogador_nome");
        lbNome.getStyleClass().add("regular-text");
        lbNome.setStyle("-fx-text-fill: #5b5b5b;");
        lbNome.setFont(new Font(22.0));

        Label lbDados = new Label("Posição: " + jogador.getPosicao()
                + "   Nacionalidade: " + jogador.getNacionalidade()
                + "   Nota: " + jogador.getNotaGeral());
        lbDados.setId("lb_jogador_dados");
        lbDados.getStyleClass().add("regular-text");
        lbDados.setStyle("-fx-text-fill: #808080;");
        lbDados.setWrapText(true);
        lbDados.setFont(new Font(18.0));
        lbDados.setPadding(new Insets(5.0, 0, 0, 0));

        // Adicionar os Labels à VBox
        vBox.getChildren().addAll(lbNome, lbDados);

        // Criar e configurar a HBox vazia
        HBox hboxVazia = new HBox();
        hboxVazia.setPrefHeight(100.0);
        hboxVazia.setPrefWidth(200.0);
        HBox.setHgrow(hboxVazia, javafx.scene.layout.Priority.ALWAYS);

        // Criar e configurar a HBox com a imagem
        HBox hboxImagem = new HBox();
        hboxImagem.setSpacing(5.0);
        hboxImagem.setAlignment(javafx.geometry.Pos.CENTER);
        HBox.setMargin(hboxImagem, new Insets(0, 5.0, 0, 0));
        hboxImagem.setOnMouseClicked(event -> {
            try {
                Adicionar_Jogador(jogador);
            } catch (TacticAllException ex) {
                Logger.getLogger(Cadastro_timeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        ImageView btnAddJogador = new ImageView(new Image(getClass().getResourceAsStream("/com/mycompany/tacticall/Imagens/plus_icon.png")));
        btnAddJogador.setId("btn_add_jogador");
        btnAddJogador.setFitHeight(32.0);
        btnAddJogador.setFitWidth(32.0);

        hboxImagem.getChildren().add(btnAddJogador);

        // Adicionar todos os elementos ao HBox
        hBox.getChildren().addAll(circle, vBox, hboxVazia, hboxImagem);
        hBox.setSpacing(0); // Ajuste o espaçamento conforme necessário

        return hBox;

    }

    private HBox hBoxTecnico(Treinador treinador) {
        HBox hBox = new HBox();
        hBox.getStyleClass().add("h_time_01");

        VBox vBox = new VBox();
        vBox.setAlignment(javafx.geometry.Pos.TOP_LEFT);
        vBox.setPadding(new Insets(10.0, 0, 0, 20.0));

        Label labelNome = new Label(treinador.getNome());
        labelNome.getStyleClass().add("regular-text");
        labelNome.setTextFill(Color.web("#5b5b5b"));
        labelNome.setFont(new Font(22.0));

        Label lbDados = new Label("Nacionalidade: " + treinador.getNacionalidade()
                + "   Nota: " + treinador.getNotaGeral());
        lbDados.setId("lb_treinador_dados");
        lbDados.getStyleClass().add("regular-text");
        lbDados.setStyle("-fx-text-fill: #808080;");
        lbDados.setWrapText(true);
        lbDados.setFont(new Font(18.0));
        lbDados.setPadding(new Insets(5.0, 0, 0, 0));

        vBox.getChildren().addAll(labelNome, lbDados);

        HBox hboxVazia = new HBox();
        hboxVazia.setPrefHeight(100.0);
        hboxVazia.setPrefWidth(200.0);
        HBox.setHgrow(hboxVazia, javafx.scene.layout.Priority.ALWAYS);

        HBox hboxImagem = new HBox();
        hboxImagem.setSpacing(5.0);
        hboxImagem.setAlignment(javafx.geometry.Pos.CENTER);
        HBox.setMargin(hboxImagem, new Insets(0, 5.0, 0, 0));
        hboxImagem.setOnMouseClicked(event -> {
            try {
                adicionarTecnico(treinador);
            } catch (TacticAllException ex) {
                Logger.getLogger(Cadastro_timeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        ImageView btnAddTecnico = new ImageView(new Image(getClass().getResourceAsStream("/com/mycompany/tacticall/Imagens/plus_icon.png")));
        btnAddTecnico.setFitHeight(32.0);
        btnAddTecnico.setFitWidth(32.0);
        hboxImagem.getChildren().add(btnAddTecnico);

        hBox.getChildren().addAll(vBox, hboxVazia, hboxImagem);

        hBox.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        hBox.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: #a4a4a4;"); // Add the appropriate style

        hBox.setPadding(new Insets(10.0, 0, 8.0, 0)); // Adjust padding as needed

        return hBox;
    }

    private void criarHBoxes() {
        Platform.runLater(() -> {
            vbox_list_jogadores.getChildren().clear();

            for (Jogador jogador : jogadores) {
                if (!jogadorEscalado(jogador)) {
                    HBox playerBox = HBox_Jogador(jogador);
                    vbox_list_jogadores.getChildren().add(playerBox);
                }
            }
        });
    }

    private void criarHBoxesTecnicos() {
        Platform.runLater(() -> {
            vbox_list_tecnicos.getChildren().clear();

            for (Treinador tecnico : tecnicos) {
                if (tecnicoSelecionado == null || tecnico.getId() != tecnicoSelecionado.getId()) {
                    HBox tecnicoBox = hBoxTecnico(tecnico);
                    vbox_list_tecnicos.getChildren().add(tecnicoBox);
                }
            }
        });
    }

    private boolean jogadorEscalado(Jogador j) {
        for (Jogador x : jogadores_escalados) {
            if (x.getId() == j.getId()) {
                return true;
            }
        }
        return false;
    }

    private void criarHBoxEscalados(List<Jogador> j) {
        Platform.runLater(() -> {
            vbox_jogadores_e.getChildren().clear();
            for (Jogador x : j) {
                HBox playerBox = hBoxJogadorEscalado(x);
                vbox_jogadores_e.getChildren().add(playerBox);
            }
        });
    }

    private HBox hBoxJogadorEscalado(Jogador jogador) {
        HBox hBox = new HBox();

        // Configuração da HBox
        hBox.setPrefHeight(98.0);
        hBox.setPrefWidth(212.0);
        hBox.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: #404040;");
        hBox.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        hBox.setPadding(new Insets(10.0, 0, 4.0, 0));

        // Adicionando o círculo
        Circle circle = new Circle(34.0);
        circle.setFill(javafx.scene.paint.Color.web("#7c7c7c"));
        circle.setStroke(javafx.scene.paint.Color.web("#575757"));
        circle.setStrokeType(StrokeType.INSIDE);
        circle.setStrokeWidth(2.0);

        if (!jogador.getImagem().equals("null")) {
            String imageUrl = jogador.getImagem(); // Substitua pelo URL real
            Image image = new Image(imageUrl);
            circle.setFill(new ImagePattern(image));
        }

        VBox vboxInterna = new VBox();
        vboxInterna.setPadding(new Insets(10.0, 0, 0, 20.0));
        vboxInterna.setAlignment(javafx.geometry.Pos.TOP_LEFT);

        // Adicionando labels na VBox interna
        Label labelNome = new Label(jogador.getNome());
        labelNome.getStyleClass().add("regular-text");
        labelNome.setStyle("-fx-text-fill: #d3d3d3;");
        labelNome.setFont(new Font(16.0));

        Label labelDados = new Label("Posição: " + jogador.getPosicao()
                + "   Nacionalidade: " + jogador.getNacionalidade()
                + "   Nota: " + jogador.getNotaGeral());
        labelDados.getStyleClass().add("regular-text");
        labelDados.setStyle("-fx-text-fill: #c7c7c7;");
        labelDados.setWrapText(true);
        labelNome.setFont(new Font(12.0));
        labelDados.setPadding(new Insets(5.0, 0, 0, 0));
        labelDados.setMaxWidth(Double.MAX_VALUE);
        vboxInterna.getChildren().addAll(labelNome, labelDados);

        // Criar e configurar a HBox vazia
        HBox hboxVazia = new HBox();
        hboxVazia.setPrefHeight(100.0);
        hboxVazia.setPrefWidth(1.0);
        HBox.setHgrow(hboxVazia, javafx.scene.layout.Priority.ALWAYS);

        // Criar e configurar a HBox com a imagem
        HBox hboxImagem = new HBox();
        hboxImagem.setSpacing(5.0);
        hboxImagem.setAlignment(javafx.geometry.Pos.CENTER);
        HBox.setMargin(hboxImagem, new Insets(0, 5.0, 0, 0));
        hboxImagem.setOnMouseClicked(event -> {
            try {
                remover_Jogador(jogador);
            } catch (TacticAllException ex) {
                Logger.getLogger(Cadastro_timeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/com/mycompany/tacticall/Imagens/x_icon.png")));
        imageView.setFitHeight(32.0);
        imageView.setFitWidth(32.0);

        hboxImagem.getChildren().add(imageView);

        // Adicionando elementos à HBox
        hBox.getChildren().addAll(circle, vboxInterna, hboxVazia, hboxImagem);
        hBox.setSpacing(0);
        return hBox;
    }

    public void Adicionar_Jogador(Jogador jogador) throws TacticAllException {
        jogadores_escalados.add(jogador);
        Pesquisar_Jogadores();
        Pesquisar_Jogadores_E();
    }

    public void adicionarTecnico(Treinador tecnico) throws TacticAllException {
        tecnicoSelecionado = tecnico;
        pesquisarTecnico();
        preencherTecnicoSelecionado();
    }

    public void SalvarTime() throws TacticAllException, IOException {
        if (validarCampos()) {
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
            RelacionamentoTimeProfissional rel_tecnico = new RelacionamentoTimeProfissional(tecnicoSelecionado.getId(), id_time);
            rel_dao.inserir(rel_tecnico);

            App.setRoot("times");
        }
    }

    public void Pesquisar_Jogadores() throws TacticAllException {
        JogadorDAO j = new JogadorDAO();
        jogadores = j.listarPorNome(txt_pesquisa_jogador.getText());
        criarHBoxes();
    }

    public void Pesquisar_Jogadores_E() throws TacticAllException {
        pesquisaEscalados(txt_pesquisa_jogador_e.getText());
    }

    public void remover_Jogador(Jogador j) throws TacticAllException {
        jogadores_escalados.remove(j);
        Pesquisar_Jogadores();
        Pesquisar_Jogadores_E();
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

    public void removerTecnico() throws TacticAllException {
        tecnicoSelecionado = null;
        preencherTecnicoSelecionado();
        pesquisarTecnico();
    }

    public void pesquisarTecnico() throws TacticAllException {
        TreinadorDAO t = new TreinadorDAO();
        tecnicos = t.listarPorNome(txt_pesquisa_tecnico.getText());
        criarHBoxesTecnicos();
    }

    private HBox hBoxTecnicoSelecionado() {
        HBox hBox = new HBox();
        if (tecnicoSelecionado != null) {
            // Configuração da HBox
            hBox.setPrefHeight(98.0);
            hBox.setPrefWidth(212.0);

            VBox vboxInterna = new VBox();
            HBox.setHgrow(vboxInterna, javafx.scene.layout.Priority.ALWAYS);
            vboxInterna.setSpacing(5);

            // Adicionando labels na VBox interna
            Label labelNome = new Label(tecnicoSelecionado.getNome());
            labelNome.getStyleClass().add("regular-text");
            labelNome.setTextFill(Color.LIGHTGRAY);
            labelNome.setFont(Font.font("Arial", FontWeight.BOLD, 16.0));

            Label labelDados = new Label("Nacionalidade: " + tecnicoSelecionado.getNacionalidade()
                    + "   Nota Geral: " + tecnicoSelecionado.getNotaGeral());
            labelDados.getStyleClass().add("regular-text");
            labelDados.setTextFill(Color.web("#c7c7c7"));
            labelDados.setWrapText(true);

            vboxInterna.getChildren().addAll(labelNome, labelDados);

            // Adicionando a ImageView para remover o treinador
            ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/com/mycompany/tacticall/Imagens/x_icon.png")));
            imageView.setFitHeight(32.0);
            imageView.setFitWidth(32.0);
            imageView.setOnMouseClicked(event -> {
                try {
                    removerTecnico();
                } catch (TacticAllException ex) {
                    Logger.getLogger(Cadastro_timeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            // Adicionando elementos à HBox
            hBox.getChildren().addAll(vboxInterna, imageView);

            // Configuração do padding e margens da HBox
            HBox.setMargin(vboxInterna, new Insets(0, 0, 0, 20));
            HBox.setMargin(imageView, new Insets(0, 0, 0, 20));
            HBox.setMargin(hBox, new Insets(20.0, 0, 0, 0));
        }
        return hBox;
    }

    private void preencherTecnicoSelecionado() {
        Platform.runLater(() -> {
            vbox_tecnicoSelecionado.getChildren().clear();
            vbox_tecnicoSelecionado.getChildren().add(hBoxTecnicoSelecionado());
        });
    }

    public boolean validarCampos() {
        if (txt_nome_t.getText().isEmpty() || txt_sigla_t.getText().isEmpty() || txt_pais_t.getText().isEmpty() || txt_liga_t.getText().isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.ERROR, "Por favor, preencha todos os campos.");
            alerta.setTitle("Campos em branco");
            alerta.setHeaderText("Campos obrigatórios não preenchidos.");
            alerta.showAndWait();
            return false;
        }

        if (tecnicoSelecionado == null) {
            Alert alerta = new Alert(Alert.AlertType.ERROR, "Por favor, selecione um técnico.");
            alerta.setTitle("Selecione um técnico.");
            alerta.setHeaderText("É necessário selecionar um técnico para criar seu time.");
            alerta.showAndWait();
            return false;
        }

        if (jogadores_escalados.size() != 11) {
            Alert alerta = new Alert(Alert.AlertType.ERROR, "Você deve escalar 11 jogadores para criar um time.");
            alerta.setTitle("Selecione 11 jogadores.");
            alerta.setHeaderText("É necessário escalar 11 jogadores para criar seu time.");
            alerta.showAndWait();
            return false;
        }
        return true;
    }

}
