/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package hollowmemory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author david
 */
public class GameSettingsController implements Initializable {

    @FXML
    private AnchorPane gameSettingsPane;
    @FXML
    private Button salir;
    @FXML
    private Button IniciarJuego;
    @FXML
    private ToggleGroup dificultGroup;
    @FXML
    private ToggleGroup playersGroup;
    @FXML
    private TextField playerOne;
    @FXML
    private RadioButton easyMode;
    @FXML
    private RadioButton normalMode;
    @FXML
    private RadioButton hardMode;
    @FXML
    private RadioButton playerVSpc;
    @FXML
    private RadioButton playerVSplayer;
    @FXML
    private TextField playerTwo;
    @FXML
    private TextField txfl_GameTime;
    @FXML
    private RadioButton blessing;
    @FXML
    private RadioButton malediction;
    @FXML
    private Label warning;
    //variables
    private String difficulty;
    private int typeDifficult = 2;
    private String players;
    private int typePlayers = 2;
    private Boolean zoteBlessing = false, radianceMaledic = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txfl_GameTime.setDisable(true);
        malediction.setDisable(true);
    }

    @FXML
    private void BackToStart(ActionEvent event) throws InterruptedException {
        Reproductor objReproductor = new Reproductor();
        String Sound = "src/sounds/clicks.mp3";
        objReproductor.CargarMusica(Sound);
        objReproductor.Play();
        Thread.sleep(300);
        try {
            HollowMemory.setRoot("FXMLHollow");
        } catch (IOException ex) {
            Logger.getLogger(GameSettingsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Initialize(ActionEvent event) throws InterruptedException, IOException {
        Reproductor objReproductor = new Reproductor();
        String Sound = "src/sounds/play.wav";
        objReproductor.CargarMusica(Sound);
        objReproductor.Play();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/hollowmemory/Tablero.fxml"));
        Parent root = loader.load();
        TableroController controller = loader.getController();
        Scene scene = new Scene(root, 1500, 800);
        Stage stage = new Stage();
        stage.setScene(scene);
        controller.initParam(zoteBlessing, radianceMaledic, typePlayers, typeDifficult, playerOne.getText(), playerTwo.getText(), txfl_GameTime.getText(), stage);
        stage.setTitle("Hollow Memory");
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    @FXML
    private void UseBlessing(ActionEvent event) throws InterruptedException {
        Reproductor objReproductor = new Reproductor();
        String Sound = "src/sounds/BlessingSelected.mp3";
        objReproductor.CargarMusica(Sound);
        objReproductor.Play();
        Thread.sleep(300);
        this.zoteBlessing = this.zoteBlessing == false;

    }

    @FXML
    private void difficulty(ActionEvent event) throws InterruptedException {
        Reproductor objReproductor = new Reproductor();
        String Sound = "src/sounds/changeSelection.wav";
        objReproductor.CargarMusica(Sound);
        objReproductor.Play();
        Thread.sleep(300);
        this.difficulty = ((RadioButton) event.getSource()).getText();
        switch (this.difficulty) {
            case "Facil" -> {
                this.typeDifficult = 1;
                txfl_GameTime.setDisable(true);
                blessing.setDisable(true);
                malediction.setDisable(true);
            }
            case "Normal" -> {
                this.typeDifficult = 2;
                txfl_GameTime.setDisable(true);
                blessing.setDisable(false);
                malediction.setDisable(true);
            }
            case "Dificil" -> {
                this.typeDifficult = 3;
                txfl_GameTime.setDisable(false);
                blessing.setDisable(false);
                malediction.setDisable(false);
            }
        }
    }

    @FXML
    private void Players(ActionEvent event) throws InterruptedException {
        Reproductor objReproductor = new Reproductor();
        String Sound = "src/sounds/changeSelection.wav";
        objReproductor.CargarMusica(Sound);
        objReproductor.Play();
        Thread.sleep(300);
        this.players = ((RadioButton) event.getSource()).getText();
        switch (this.players) {
            case "JUGADOR vs PC" -> {
                this.typePlayers = 1;
            }
            case "JUGADOR vs JUGADOR" -> {
                this.typePlayers = 2;
            }
        }
    }

    @FXML
    private void UseMalediction(ActionEvent event) throws InterruptedException {
        Reproductor objReproductor = new Reproductor();
        String Sound = "src/sounds/HollowKnightCard.wav";
        objReproductor.CargarMusica(Sound);
        objReproductor.Play();
        Thread.sleep(300);
        this.radianceMaledic = this.radianceMaledic == false;
    }

}
