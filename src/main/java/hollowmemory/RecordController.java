package hollowmemory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author david
 */
public class RecordController implements Initializable {

    @FXML
    private Button btn_volver;
    @FXML
    private GridPane myGrid;
    //Variables & objetos
    List<String> lineasArchivo = new ArrayList<>();
    Integer flag = 0;
    @FXML
    private Button clean;
    @FXML
    private Label mssg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            lineasArchivo.addAll(HistoryStore.readAll());
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de historial: " + e.getMessage());
        }

        for (int i = lineasArchivo.size() - 1; i >= 0; i--) {
            Label lbl = new Label(lineasArchivo.get(i));
            lbl.getStyleClass().add(0, "Label");
            myGrid.add(lbl, 0, i);

            flag++;
            if (flag == 5) {
                i = -1;
            }
        }
    }

    @FXML
    private void Salir(ActionEvent event) throws InterruptedException {
        Reproductor objReproductor = new Reproductor();
        String Sound = "/Sounds/clicks.mp3";
        objReproductor.CargarMusica(Sound);
        objReproductor.Play();
        Thread.sleep(300);
        try {
            HollowMemory.setRoot("FXMLHollow");
        } catch (IOException ex) {
            Logger.getLogger(FXMLHollowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void DeleteRecords(ActionEvent event) throws FileNotFoundException {
        Reproductor objReproductor = new Reproductor();
        String Sound = "/Sounds/clicks.mp3";
        objReproductor.CargarMusica(Sound);
        objReproductor.Play();
        try {
            HistoryStore.clear();
            lineasArchivo.clear();
            myGrid.getChildren().clear();
        } catch (IOException ex) {
            Logger.getLogger(RecordController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mssg.setText("Historial eliminado con exito");
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
                delay.setOnFinished(event1 -> {
                    mssg.setText("...");
                });
                delay.play();
        Sound="/Voices/Iselda Voice.mp3";
        objReproductor.CargarMusica(Sound);
        objReproductor.Play();
    }

}
