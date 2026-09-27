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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;



/**
 * FXML Controller class
 *
 * @author david
 */
public class FXMLCreditsController implements Initializable {
    
    @FXML
    private AnchorPane creditsPane;
    @FXML
    private Button btnCloseCredits;
    //variables
    private Integer flag=0;
    private Reproductor objReproductor = new Reproductor();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String credits= "/Sounds/creditpane.mp3";
        this.objReproductor.CargarMusica(credits);
        this.objReproductor.Play();
        this.flag=1;
    }    
    
    @FXML
    private void CloseWindow(ActionEvent event) throws InterruptedException {
        Reproductor Click= new Reproductor();
        String Sound = "/Sounds/clicks.mp3";
        Click.CargarMusica(Sound);
        Click.Play();
        Thread.sleep(300);
        if(flag==1){
        this.objReproductor.PauseSong();
            try {
                HollowMemory.setRoot("FXMLHollow");
            } catch (IOException ex) {
                Logger.getLogger(FXMLCreditsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            try { 
                HollowMemory.setRoot("FXMLHollow");
            } catch (IOException ex) {
                Logger.getLogger(FXMLCreditsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
