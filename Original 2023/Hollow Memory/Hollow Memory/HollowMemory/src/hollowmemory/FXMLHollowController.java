/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package hollowmemory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author david
 */
public class FXMLHollowController implements Initializable {
  
    @FXML
    private Button credits;
    @FXML
    private Button Salir;
    @FXML
    private Button Play;
    @FXML
    private AnchorPane portadaPane;
    //Variables & Objetos
    private final Reproductor objReproductor1= new Reproductor();
    private int flag=0;
    @FXML
    private Button history;
    @FXML
    private void creditsWindow(ActionEvent event) throws IOException, InterruptedException {
        Reproductor objReproductor= new Reproductor();
        String Sound = "src/sounds/clicks.mp3";
        objReproductor.CargarMusica(Sound);
        objReproductor.Play();
        Thread.sleep(300);
        try {
            HollowMemory.setRoot("FXMLCredits");
        } catch (IOException ex) {
            Logger.getLogger(FXMLHollowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(flag==1){
        this.objReproductor1.PauseSong();
        this.flag=0;
        }
    }
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String SoundLocation = "src/sounds/Portada Sound.mp3";
        this.objReproductor1.CargarMusica(SoundLocation);
        this.objReproductor1.Play();
        this.flag=1;
    }  

    @FXML
    private void CloseWindow(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void OpenPanelPlay(ActionEvent event) throws InterruptedException {
        Reproductor objReproductor= new Reproductor();
        String Sound = "src/sounds/clicks.mp3";
        objReproductor.CargarMusica(Sound);
        objReproductor.Play();
        Thread.sleep(300);
        try {
            HollowMemory.setRoot("GameSettings");
        } catch (IOException ex) {
            Logger.getLogger(FXMLHollowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(flag==1){
        this.objReproductor1.PauseSong();
        this.flag=0;
        }   
    }

    @FXML
    private void OpenRecord(ActionEvent event) throws InterruptedException {
        Reproductor objReproductor= new Reproductor();
        String Sound = "src/sounds/clicks.mp3";
        objReproductor.CargarMusica(Sound);
        objReproductor.Play();
        Thread.sleep(300);
        try {
            HollowMemory.setRoot("Record");
        } catch (IOException ex) {
            Logger.getLogger(FXMLHollowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(flag==1){
        this.objReproductor1.PauseSong();
        this.flag=0;
        }
    }
}