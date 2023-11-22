/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.tacticall;

import com.mycompany.dao.EsquemaTaticoDAO;
import com.mycompany.dao.JogadorDAO;
import com.mycompany.dao.RelacionamentoJogadorEsquemaDAO;
import com.mycompany.dao.TimeDAO;
import com.mycompany.dao.UsuarioDAO;
import com.mycompany.exception.TacticAllException;
import com.mycompany.model.EsquemaTatico;
import com.mycompany.model.Jogador;
import com.mycompany.model.RelacionamentoJogadorEsquema;
import com.mycompany.model.Time;
import com.mycompany.model.Usuario;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author moond
 */
public class Editar_esquema_taticoController extends Sidebar implements Initializable {

    private EsquemaTatico esquemaAtual = new EsquemaTatico();
    List<Time> times;
    List<Jogador> jogadores = new ArrayList<>();
    Time timeAtual;
    List<RelacionamentoJogadorEsquema> posicoes = new ArrayList<>();

    @FXML
    TextField txt_nome_es;
    @FXML
    TextField txt_pesquisa_jogadores;
    @FXML
    ComboBox cb_time_es;
    @FXML
    ComboBox cb_categ_es;
    @FXML
    VBox vbox_list_jogadores;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            VerificaLogin();
            esquemaAtual = Sessao.getInstancia().getEsquemaTaticoEditando();
            PreencheComboBox();
            preencherDados();
        } catch (IOException | TacticAllException ex) {
            Logger.getLogger(Cadastro_esquema_taticoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void preencherDados() throws TacticAllException {
        txt_nome_es.setText(esquemaAtual.getNome());
        TimeDAO timeDAO = new TimeDAO();
        Time time = timeDAO.listarPorID(esquemaAtual.getIdTime());
        cb_time_es.setValue(time.getNome());
        cb_categ_es.setValue(esquemaAtual.getTipo());
        preencherPosicoes();
        preencherTime();
    }

    private void preencherPosicoes() throws TacticAllException {
        RelacionamentoJogadorEsquemaDAO relDAO = new RelacionamentoJogadorEsquemaDAO();
        posicoes = relDAO.listarPorEsquema(esquemaAtual);
    }

    private void PreencheComboBox() throws TacticAllException {
        String email = Sessao.getInstancia().getEmail();
        UsuarioDAO dao = new UsuarioDAO();
        Usuario user = dao.listarPorEmail(email);
        TimeDAO timeDAO = new TimeDAO();
        times = timeDAO.listarPorUsuarioId(user.getId());
        List<String> listaTime = new ArrayList<String>();
        for (Time time : times) {
            listaTime.add(time.getNome());
        }
        cb_time_es.getItems().addAll(listaTime);
        cb_categ_es.getItems().add("Ofensivo");
        cb_categ_es.getItems().add("Defensivo");

    }

    public void alterarTime() throws TacticAllException {
        posicoes.clear();
        TimeDAO timeDAO = new TimeDAO();
        timeAtual = timeDAO.listarPorNome((String) cb_time_es.getSelectionModel().getSelectedItem()).get(0);
        pesquisarJogadores();
    }
        public void preencherTime() throws TacticAllException {
        TimeDAO timeDAO = new TimeDAO();
        timeAtual = timeDAO.listarPorNome((String) cb_time_es.getSelectionModel().getSelectedItem()).get(0);
        pesquisarJogadores();
    }
        
    public void pesquisarJogadores() throws TacticAllException {
        JogadorDAO j = new JogadorDAO();
        jogadores = j.listarPorTime(timeAtual.getId());
        criarHBoxes();
    }

    private void criarHBoxes() {
        Platform.runLater(() -> {
            vbox_list_jogadores.getChildren().clear();

            for (Jogador jogador : jogadores) {
                String jogadorPosicao = getJogadorPosicao(jogador);
                HBox playerBox = HBox_Jogador(jogador, jogadorPosicao);
                vbox_list_jogadores.getChildren().add(playerBox);
            }
        });
    }

    private String getJogadorPosicao(Jogador jogador) {
        for (RelacionamentoJogadorEsquema rel : posicoes) {
            if (jogador.getId() == rel.getIdJogador()) {
                return rel.getPosicao();
            }
        }
        return ""; // Return an empty string if the position is not found
    }

    private HBox HBox_Jogador(Jogador jogador, String pos) {
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
        if (!"null".equals(jogador.getImagem())) {
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

        VBox vboxPosicao = new VBox();
        vboxPosicao.setSpacing(5.0);

        Label lbPosicao = new Label("Posição:");
        lbPosicao.getStyleClass().add("bold-text");
        lbPosicao.setStyle("-fx-text-fill: #5b5b5b;");
        lbPosicao.setFont(new Font(22.0));

        ComboBox<String> cbPosicaoJogador = new ComboBox<>();
        cbPosicaoJogador.setId("cb_posicao_jogador");
        cbPosicaoJogador.getStyleClass().add("txt-box");
        // Adicione itens ao ComboBox conforme necessário
        cbPosicaoJogador.getItems().addAll("Atacante", "Meio-Campista", "Defensor", "Goleiro");

        if (!pos.equals("")) {
            cbPosicaoJogador.setValue(pos);
        }

        // Adicione um ouvinte de eventos à combobox
        cbPosicaoJogador.setOnAction(event -> {
            // Obtém o jogador associado ao HBox
            Jogador jogadorAtual = jogadores.get(vbox_list_jogadores.getChildren().indexOf(hBox));

            // Obtém a nova posição selecionada na combobox
            String novaPosicao = cbPosicaoJogador.getSelectionModel().getSelectedItem();

            // Chama o método AlterarPosicao
            AlterarPosicao(jogadorAtual, novaPosicao);
        });

        vboxPosicao.getChildren().addAll(lbPosicao, cbPosicaoJogador);

        // Adicionar todos os elementos ao HBox
        hBox.getChildren().addAll(circle, vBox, hboxVazia, vboxPosicao);
        hBox.setSpacing(0); // Ajuste o espaçamento conforme necessário

        return hBox;

    }

    private void AlterarPosicao(Jogador j, String posicao) {
        HashMap<Integer, String> jogadorPosicoes = new HashMap<>();
        for (RelacionamentoJogadorEsquema r : posicoes) {
            jogadorPosicoes.put(r.getIdJogador(), r.getPosicao());
        }

        if (jogadorPosicoes.containsKey(j.getId())) {
            jogadorPosicoes.replace(j.getId(), posicao);
        } else {
            jogadorPosicoes.put(j.getId(), posicao);
        }

        // Update the posicoes list based on the jogadorPosicoes map
        posicoes.clear();
        for (Map.Entry<Integer, String> entry : jogadorPosicoes.entrySet()) {
            RelacionamentoJogadorEsquema r = new RelacionamentoJogadorEsquema();
            r.setIdJogador(entry.getKey());
            r.setPosicao(entry.getValue());
            posicoes.add(r);
        }
    }

    public void Salvar_Esquema_Tatico() throws TacticAllException, IOException {
        if (validarCampos()) {
            EsquemaTaticoDAO eDAO = new EsquemaTaticoDAO();
            String tipo = (String) cb_categ_es.getSelectionModel().getSelectedItem();
            EsquemaTatico esquema = eDAO.listarPorID(esquemaAtual.getId());
            esquema.setIdTime(timeAtual.getId());
            esquema.setNome(txt_nome_es.getText());
            esquema.setTipo(tipo);

            int countDefesa = 0;
            int countAtaque = 0;
            int countMeio = 0;

            for (RelacionamentoJogadorEsquema rel : posicoes) {
                if ("Defensor".equals(rel.getPosicao())) {
                    countDefesa++;
                }
                if ("Atacante".equals(rel.getPosicao())) {
                    countAtaque++;
                }
                if ("Meio-Campista".equals(rel.getPosicao())) {
                    countMeio++;
                }
            }
            String formacao = countDefesa + "-" + countMeio + "-" + countAtaque;
            esquema.setFormacao(formacao);
            esquema.setTaticaEspecifica("");
            eDAO.alterar(esquema);
            RelacionamentoJogadorEsquemaDAO relDAO = new RelacionamentoJogadorEsquemaDAO();
            relDAO.removerDoEsquema(esquema.getId());
            for (RelacionamentoJogadorEsquema rel : posicoes) {
                rel.setIdEsquema(esquema.getId());
                relDAO.inserir(rel);
            }
            App.setRoot("esquemas_taticos");
        }
    }

    public boolean validarCampos() throws TacticAllException {
        if (txt_nome_es.getText().isEmpty() || cb_categ_es.getSelectionModel().getSelectedItem() == null) {
            Alert alerta = new Alert(Alert.AlertType.ERROR, "Por favor, preencha todos os campos.");
            alerta.setTitle("Campos em branco");
            alerta.setHeaderText("Campos obrigatórios não preenchidos.");
            alerta.showAndWait();
            return false;
        }

        if (posicoes.size() != 11) {
            Alert alerta = new Alert(Alert.AlertType.ERROR, "Escolha uma posição para todos os jogadores.");
            alerta.setTitle("Defina as posições");
            alerta.setHeaderText("É necessário escolher uma posição para todos os jogadores.");
            alerta.showAndWait();
            return false;
        }

        int goleiroCount = 0;
        for (RelacionamentoJogadorEsquema rel : posicoes) {
            if ("Goleiro".equals(rel.getPosicao())) {
                goleiroCount++;
            }
        }

        if (goleiroCount == 0) {
            Alert alerta = new Alert(Alert.AlertType.ERROR, "Por favor, escolha um goleiro.");
            alerta.setTitle("Goleiro não selecionado");
            alerta.setHeaderText("É obrigatório escolher um goleiro para o esquema tático.");
            alerta.showAndWait();
            return false;
        } else if (goleiroCount > 1) {
            Alert alerta = new Alert(Alert.AlertType.ERROR, "Apenas um goleiro é permitido.");
            alerta.setTitle("Número incorreto de goleiros");
            alerta.setHeaderText("Só é permitido escolher um goleiro para o esquema tático.");
            alerta.showAndWait();
            return false;
        }

        EsquemaTaticoDAO esDAO = new EsquemaTaticoDAO();
        List<EsquemaTatico> esquemas = esDAO.listarEsquemasPorTime(timeAtual.getId());
        boolean existe = false;
        for (EsquemaTatico e : esquemas) {
            if (e.getId()!= esquemaAtual.getId() && e.getNome().equals(txt_nome_es.getText())) {
                existe = true;
                break;
            }
        }

        if (existe) {
            Alert alerta = new Alert(Alert.AlertType.ERROR, "Problemas com o campo: Nome");
            alerta.setTitle("Nome existente");
            alerta.setHeaderText("O nome deste esquema tático já foi cadastrado.");
            alerta.showAndWait();
            return false;
        }

        return true;
    }
}
