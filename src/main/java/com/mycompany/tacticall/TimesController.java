/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.tacticall;

import com.mycompany.dao.TimeDAO;
import com.mycompany.exception.TacticAllException;
import com.mycompany.model.Time;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
public class TimesController extends Sidebar implements Initializable {

    List<Time> times = new ArrayList<Time>();
    @FXML
    TextField txt_pesquisa_time;
    @FXML
    VBox vbox_lista_times;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            VerificaLogin();
            Pesquisar_Times();
        } catch (IOException ex) {
            Logger.getLogger(TimesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TacticAllException ex) {
            Logger.getLogger(TimesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Ir_Criar_Time() throws IOException {
        App.setRoot("cadastro_time");
    }

    public void Pesquisar_Times() throws TacticAllException {
        TimeDAO tDao = new TimeDAO();
        times = tDao.listarPorNome(txt_pesquisa_time.getText());
        criarHBoxes();
    }

    private void criarHBoxes() {
        Platform.runLater(() -> {
            vbox_lista_times.getChildren().clear();

            for (Time time : times) {
                HBox hBox = hBoxTime(time);
                vbox_lista_times.getChildren().add(hBox);
            }
        });
    }

    private HBox hBoxTime(Time time) {
        HBox hbox = new HBox();
        Circle circle = new Circle(48.0, Color.web("#c6c6c6"));
        circle.setStroke(Color.web("#ebebeb"));
        circle.setStrokeWidth(3.0);
        circle.setFill(Color.web(time.getCorUniforme()));

        // Criando a VBox interna
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(0, 0, 0, 20));

        // Criando o Label para o nome do time
        Label nomeLabel = new Label(time.getNome() + "(" + time.getSigla() + ")");
        nomeLabel.getStyleClass().add("regular-text");
        nomeLabel.setStyle("-fx-text-fill: #5b5b5b;");
        nomeLabel.setFont(new Font(22.0));

        // Criando o Label para os dados do time
        Label dadosLabel = new Label("País: " + time.getPais() + "   Liga: " + time.getLiga());
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
                Editar_Time(time);
            } catch (IOException | TacticAllException ex) {
                Logger.getLogger(TimesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        ImageView btnEditarTime = new ImageView(new Image(getClass().getResourceAsStream("/com/mycompany/tacticall/Imagens/gear_icon.png")));
        btnEditarTime.setId("btn_add_jogador");
        btnEditarTime.setFitHeight(32.0);
        btnEditarTime.setFitWidth(32.0);

        editarHBox.getChildren().add(btnEditarTime);

        HBox removerHBox = new HBox();
        removerHBox.setSpacing(5.0);
        removerHBox.setAlignment(javafx.geometry.Pos.CENTER);
        HBox.setMargin(removerHBox, new Insets(0, 5.0, 0, 0));
        removerHBox.setOnMouseClicked(event -> {
            try {
                Remover_Time(time);
            } catch (TacticAllException ex) {
                Logger.getLogger(TimesController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(TimesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        ImageView btnRemoverTime = new ImageView(new Image(getClass().getResourceAsStream("/com/mycompany/tacticall/Imagens/trashcan_icon.png")));
        btnRemoverTime.setId("btn_add_jogador");
        btnRemoverTime.setFitHeight(32.0);
        btnRemoverTime.setFitWidth(32.0);

        removerHBox.getChildren().add(btnRemoverTime);

        // Adicionando os elementos à HBox principal
        hbox.getChildren().addAll(circle, vbox, emptyHBox, editarHBox, removerHBox);
        hbox.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        hbox.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: #a4a4a4;");
        hbox.setPadding(new Insets(0, 0, 8.0, 0));
        VBox.setMargin(hbox, new Insets(10.0, 0, 0, 0));
        return hbox;
    }

    public void Editar_Time(Time t) throws IOException, TacticAllException {
        Sessao.getInstancia().setTimeEditando(t);
        App.setRoot("editar_time");
    }

    public void Remover_Time(Time t) throws TacticAllException, IOException {
        // Cria um alerta de confirmação
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação de Exclusão");
        alert.setHeaderText("Excluir Time");
        alert.setContentText("Tem certeza que deseja excluir o time?\nEssa ação irá remover todos os esquemas táticos e simulações associadas.");

        // Obtém a resposta do usuário
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

        // Se o usuário confirmar, então remova o time
        if (result == ButtonType.OK) {
            try {
                TimeDAO tDAO = new TimeDAO();
                tDAO.remover(t);
                App.setRoot("times");
            } catch (TacticAllException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
