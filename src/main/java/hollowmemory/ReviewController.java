/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package hollowmemory;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author david
 */
public class ReviewController implements Initializable {

    @FXML
    private GridPane GridPaneReview;
    @FXML
    private Button close;
    @FXML
    private Button show;
    
    private Stage stage1;
    private int row=0;
    private BoardCards boardcards;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void CloseReview(ActionEvent event) {
        this.stage1.close();
    }
    
    public void initParamR(BoardCards boardcards1, int row,Stage stage) throws InterruptedException, FileNotFoundException{
        
        this.stage1=stage;
        this.row=row;
        this.boardcards=boardcards1;
        
    }

    @FXML
    private void RunReview(ActionEvent event) {
        try {
            
            SetInformation(this.row);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    private void SetInformation(int row) throws FileNotFoundException{
     for (int i = 0; i < row; i++) {
            for (int j = 0; j < 4; j++) {
                String image = boardcards.boardCards[i][j].CardImage;
                Image selecImage = ResourceHelper.image("/CardsImages/" + image + ".png");
                ImageView viewCard= new ImageView(selecImage);
                GridPaneReview.add(viewCard,i,j);
            }
        }
    }

}
