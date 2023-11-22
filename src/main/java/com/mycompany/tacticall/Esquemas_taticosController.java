/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.tacticall;

import com.mycompany.dao.EsquemaTaticoDAO;
import com.mycompany.dao.TimeDAO;
import com.mycompany.dao.UsuarioDAO;
import com.mycompany.exception.TacticAllException;
import com.mycompany.model.EsquemaTatico;
import com.mycompany.model.Time;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author moond
 */
public class Esquemas_taticosController extends Sidebar implements Initializable {

    List<EsquemaTatico> esquemasTaticos = new ArrayList<EsquemaTatico>();
    List<Time> times;

    @FXML
    TextField txt_pesquisa_esquema;
    @FXML
    VBox vbox_lista_esquemas;
    @FXML
    ComboBox cb_time;
    @FXML
    ComboBox cb_categoria;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            VerificaLogin();
            PreencheComboBox();
            Pesquisar_EsquemasTaticos();
        } catch (IOException ex) {
            Logger.getLogger(Esquemas_taticosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TacticAllException ex) {
            Logger.getLogger(Esquemas_taticosController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        cb_time.getItems().addAll(listaTime);
        cb_categoria.getItems().add("Todos");
        cb_categoria.getItems().add("Ofensivo");
        cb_categoria.getItems().add("Defensivo");
        cb_categoria.setValue("Todos");
    }

    public void Ir_Criar_EsquemaTatico() throws IOException {
        App.setRoot("cadastro_esquema_tatico");
    }

    public void Pesquisar_EsquemasTaticos() throws TacticAllException {
        if (cb_time.getSelectionModel().getSelectedItem() != null) {
            String nometime = (String) cb_time.getSelectionModel().getSelectedItem();
            TimeDAO timeDAO = new TimeDAO();
            Time time = timeDAO.listarPorNome(nometime, Sessao.getInstancia().getId()).get(0);
            EsquemaTaticoDAO etDao = new EsquemaTaticoDAO();
            String categoria = (String) cb_categoria.getSelectionModel().getSelectedItem();
            if (categoria.equals("Todos")) {
                esquemasTaticos = etDao.listarPorNome(txt_pesquisa_esquema.getText(), time.getId());
            } else {
                esquemasTaticos = etDao.listarPorNome(txt_pesquisa_esquema.getText(), time.getId(), categoria);
            }
            criarHBoxes();
        }

    }

    private void criarHBoxes() {
        Platform.runLater(() -> {
            vbox_lista_esquemas.getChildren().clear();

            for (EsquemaTatico esquemaTatico : esquemasTaticos) {
                HBox hBox = hBoxEsquemaTatico(esquemaTatico);
                vbox_lista_esquemas.getChildren().add(hBox);
            }
        });
    }

    private HBox hBoxEsquemaTatico(EsquemaTatico esquemaTatico) {
        HBox hbox = new HBox();
        Label formacao = new Label(esquemaTatico.getFormacao());
        formacao.setFont(new Font(28.0));
        formacao.getStyleClass().add("bold-text");

        // Criando a VBox interna
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(0, 0, 0, 20));

        // Criando o Label para o nome do esquema tático
        Label nomeLabel = new Label(esquemaTatico.getNome());
        nomeLabel.getStyleClass().add("regular-text");
        nomeLabel.setStyle("-fx-text-fill: #5b5b5b;");
        nomeLabel.setFont(new Font(22.0));

        // Criando o Label para os dados do esquema tático
        Label dadosLabel = new Label("Descrição");
        dadosLabel.getStyleClass().add("regular-text");
        dadosLabel.setStyle("-fx-text-fill: #808080;");
        dadosLabel.setFont(new Font(18.0));
        dadosLabel.setWrapText(true);
        VBox.setMargin(dadosLabel, new Insets(5.0, 0, 0, 0));

        // Adicionando os Labels à VBox
        vbox.getChildren().addAll(nomeLabel, dadosLabel);

        // Criando a HBox vazia com crescimento horizontal
        HBox emptyHBox = new HBox();
        HBox.setHgrow(emptyHBox, javafx.scene.layout.Priority.ALWAYS);

        HBox editarHBox = new HBox();
        editarHBox.setSpacing(5.0);
        editarHBox.setAlignment(javafx.geometry.Pos.CENTER);
        HBox.setMargin(editarHBox, new Insets(0, 5.0, 0, 0));
        editarHBox.setOnMouseClicked((MouseEvent event) -> {
            try {
                Editar_EsquemaTatico(esquemaTatico);
            } catch (IOException | TacticAllException ex) {
                Logger.getLogger(Esquemas_taticosController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        ImageView btnEditarEsquemaTatico = new ImageView(new Image(getClass().getResourceAsStream("/com/mycompany/tacticall/Imagens/gear_icon.png")));
        btnEditarEsquemaTatico.setId("btn_add_jogador");
        btnEditarEsquemaTatico.setFitHeight(32.0);
        btnEditarEsquemaTatico.setFitWidth(32.0);

        editarHBox.getChildren().add(btnEditarEsquemaTatico);

        HBox removerHBox = new HBox();
        removerHBox.setSpacing(5.0);
        removerHBox.setAlignment(javafx.geometry.Pos.CENTER);
        HBox.setMargin(removerHBox, new Insets(0, 5.0, 0, 0));
        removerHBox.setOnMouseClicked(event -> {
            try {
                Remover_EsquemaTatico(esquemaTatico);
            } catch (TacticAllException ex) {
                Logger.getLogger(Esquemas_taticosController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Esquemas_taticosController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        ImageView btnRemoverEsquemaTatico = new ImageView(new Image(getClass().getResourceAsStream("/com/mycompany/tacticall/Imagens/trashcan_icon.png")));
        btnRemoverEsquemaTatico.setId("btn_add_jogador");
        btnRemoverEsquemaTatico.setFitHeight(32.0);
        btnRemoverEsquemaTatico.setFitWidth(32.0);

        removerHBox.getChildren().add(btnRemoverEsquemaTatico);

        // Adicionando os elementos à HBox principal
        hbox.getChildren().addAll(formacao, vbox, emptyHBox, editarHBox, removerHBox);
        hbox.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        hbox.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: #a4a4a4;");
        hbox.setPadding(new Insets(0, 0, 8.0, 0));
        VBox.setMargin(hbox, new Insets(10.0, 0, 0, 0));
        return hbox;
    }

    public void Editar_EsquemaTatico(EsquemaTatico et) throws IOException, TacticAllException {
        Sessao.getInstancia().setEsquemaTaticoEditando(et);
        App.setRoot("editar_esquema_tatico");
    }

    public void Remover_EsquemaTatico(EsquemaTatico et) throws TacticAllException, IOException {
        // Cria um alerta de confirmação
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação de Exclusão");
        alert.setHeaderText("Excluir Esquema Tático");
        alert.setContentText("Tem certeza que deseja excluir o esquema tático?\nEssa ação irá remover todos os dados associados.");

        // Obtém a resposta do usuário
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

        // Se o usuário confirmar, então remova o esquema tático
        if (result == ButtonType.OK) {
            try {
                EsquemaTaticoDAO etDAO = new EsquemaTaticoDAO();
                etDAO.remover(et);
                App.setRoot("esquemas_taticos");
            } catch (TacticAllException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
