package hollowmemory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author david
 */
public class TableroController implements Initializable {

    @FXML
    private Button Close;
    @FXML
    private Label namePlayer1;
    @FXML
    private Label points1;
    @FXML
    private Label namePlayer2;
    @FXML
    private Label points2;
    @FXML
    private GridPane myGridPane;
    @FXML
    private Label mssg1;
    @FXML
    private Label mssg2;
    @FXML
    private Button skin1;
    @FXML
    private Button skin2;
    @FXML
    private Label winner;
    @FXML
    private Button review;
    @FXML
    private Label seconds;
    @FXML
    private Label minutes;
    @FXML
    private Label blessed2;
    @FXML
    private Label blessed1;
    @FXML
    private Label puntaZote1;
    @FXML
    private Label puntaZote2;
    @FXML
    private ImageView fondoTablero;
    @FXML
    private ImageView tablero;
    @FXML
    private ImageView shadow;
    @FXML
    private Label lbl_InfoPlayer1;
    @FXML
    private Label lbl_InfoPlayer2;

    //variables&objetos
    private Boolean flagMusic = true, flagPoints = true, flagShift = true,
            flagIA = false, blessing = false, malediction = false;
    private Stage stage;
    private final Reproductor objReproductor = new Reproductor();
    private final Reproductor voices = new Reproductor();
    private Integer player1 = 0, player2 = 0, voice = 1, amount_ofIntelligence = 0;
    private final BoardCards boardcards = new BoardCards();
    private Cell firstCard = null;
    private Cell secondCard = null;
    private Integer row = 4, maxPoints = 0, falgMusicPoints = 0, min = 30,
            seg = 0, zoteBlessing1 = 0, radianceMalediction1 = 0, zoteBlessing2 = 0,
            radianceMalediction2 = 0, applyBlessing1 = 0, applyBlessing2 = 0,
            auxRow = 5, auxCol = 5, couples1 = 0, couples2 = 0, mistakes1 = 0, mistakes2 = 0;
    private final IA radiance = new IA();
    private PauseTransition delay = new PauseTransition();
    private Timeline timeline = new Timeline();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Cosas que no deberian de estar en un principio
        winner.setDisable(true);
        shadow.setDisable(true);
        lbl_InfoPlayer1.setDisable(true);
        lbl_InfoPlayer2.setDisable(true);
        skin2.setDisable(true);
    }

    @FXML
    private void BackToMenu(ActionEvent event) {
        if (flagMusic == false) {
            this.objReproductor.PauseSong();
            this.flagMusic = true;
        }
        try {
            HollowMemory.setRoot("FXMLHollow");
        } catch (IOException ex) {
            Logger.getLogger(FXMLHollowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage.close();
    }

    public void initParam(Boolean blessing, Boolean malediction, int typePlayers,
            int typeDifficult, String text, String text0, String text1, Stage stage) throws InterruptedException {
        String Sound = "/Sounds/GameplaySong.mp3";
        this.objReproductor.CargarMusica(Sound);
        this.objReproductor.Play();
        this.flagMusic = false;

        mssg1.setText("¡Me toca a mi!");
        String Sound1 = "/Voices/HornetZero.mp3";
        voices.CargarMusica(Sound1);
        voices.Play();

        this.blessing = blessing;
        this.malediction = malediction;
        this.stage = stage;
        if ("".equals(text)) {
            namePlayer1.setText("Hornet");
        } else {
            namePlayer1.setText(text);
        }
        if ("".equals(text0)) {
            namePlayer2.setText("Radiance");
        } else {
            namePlayer2.setText(text0);
        }

        switch (typeDifficult) {
            case 1 -> {
                this.amount_ofIntelligence = 1;
                this.row = 2;
                this.maxPoints = 8;
                this.falgMusicPoints = 4;
            }
            case 2 -> {
                this.amount_ofIntelligence = 2;
                this.row = 4;
                this.maxPoints = 16;
                this.falgMusicPoints = 10;
            }
            case 3 -> {
                this.amount_ofIntelligence = 3;
                this.row = 4;
                this.maxPoints = 16;
                this.falgMusicPoints = 10;
            }
        }
        if (typeDifficult == 3) {
            if (!"".equals(text1)) {
                try {
                    min = Integer.valueOf(text1);
                    minutes.setText(Integer.toString(min));
                } catch (NumberFormatException e) {
                    System.out.println("Puso mal los minutos");
                }
            }
            PartyTime();
        }

        if (typePlayers == 1) {
            flagIA = true;
        }

        try {
            InitializeCards();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TableroController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void InitializeCards() throws FileNotFoundException {
        boardcards.setRow(row);
        boardcards.MakeCard_Board();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < 4; j++) {
                Image cardImage = ResourceHelper.image("/CardsImages/Carta.png");
                ImageView viewCard = new ImageView(cardImage);
                viewCard.setFitWidth(98);
                viewCard.setFitHeight(158);
                viewCard.setUserData(i + "," + j);
                viewCard.setOnMouseClicked(event -> {
                    try {
                        CardListener(event);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(TableroController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                myGridPane.add(viewCard, i, j);
            }
        }

    }

    private void CardListener(javafx.scene.input.MouseEvent event) throws FileNotFoundException {
        Reproductor CardSelec = new Reproductor();
        String Sound = "/Sounds/CardSelection.mp3";
        CardSelec.CargarMusica(Sound);
        CardSelec.Play();
        Node selecOrigin = (Node) event.getSource();
        String RowAndCol = (String) selecOrigin.getUserData();

        int rowSelec = Integer.parseInt(RowAndCol.split(",")[0]);
        int colSelec = Integer.parseInt(RowAndCol.split(",")[1]);

        String image = boardcards.boardCards[rowSelec][colSelec].CardImage;
        Image selecImage = ResourceHelper.image("/CardsImages/" + image + ".png");
        ((ImageView) selecOrigin).setImage(selecImage);

        if (auxRow == 5 && auxCol == 5) {
            auxCol = colSelec;
            auxRow = rowSelec;
            checkCouples(rowSelec, colSelec);
        } else {
            if (auxRow == rowSelec && auxCol == colSelec) {
                System.out.println("Opcion repedida");
            } else {
                checkCouples(rowSelec, colSelec);
                auxCol = 5;
                auxRow = 5;
            }
        }

    }

    public void checkCouples(int rowSelec, int colSelec) throws FileNotFoundException {
        if (firstCard == null) {
            if (boardcards.boardCards[rowSelec][colSelec].cardGuessed != true) {
                firstCard = boardcards.boardCards[rowSelec][colSelec];
            }
        } else {
            if (boardcards.boardCards[rowSelec][colSelec].cardGuessed != true) {
                secondCard = boardcards.boardCards[rowSelec][colSelec];
            }
            if (secondCard != null && firstCard != secondCard) {
                if (firstCard.CardImage.equals(secondCard.CardImage)) {
                    boardcards.boardCards[firstCard.row][firstCard.column].cardGuessed = true;
                    boardcards.boardCards[secondCard.row][secondCard.column].cardGuessed = true;
                    String music = "/Voices/ParejaCorrecta.mp3";
                    voices.CargarMusica(music);
                    voices.Play();

                    if (flagPoints == true) {
                        if (blessing == true) {
                            applyBlessing1++;
                            if (applyBlessing1 == 2) {
                                zoteBlessing1++;
                                applyBlessing1 = 0;
                                String Sound = "/Voices/Zotee.mp3";
                                voices.CargarMusica(Sound);
                                voices.Play();
                                puntaZote1.setText("+1 PuntaZote!");
                                delay = new PauseTransition(Duration.seconds(2));
                                delay.setOnFinished(event -> {
                                    puntaZote1.setText("");
                                });
                                delay.play();
                            }
                        }
                        player1 += 2;
                        couples1++;
                        points1.setText(Integer.toString((player1 + zoteBlessing1) - radianceMalediction1));
                        blessed1.setText(Integer.toString(zoteBlessing1));
                    } else {
                        if (blessing == true) {
                            applyBlessing2++;
                            if (applyBlessing2 == 2) {
                                zoteBlessing2++;
                                applyBlessing2 = 0;
                                String Sound = "/Voices/Zotee.mp3";
                                voices.CargarMusica(Sound);
                                voices.Play();
                                puntaZote2.setText("+1 PuntaZote!");
                                delay = new PauseTransition(Duration.seconds(2));
                                delay.setOnFinished(event -> {
                                    puntaZote2.setText("");
                                });
                                delay.play();
                            }
                        }
                        player2 += 2;
                        couples2++;
                        points2.setText(Integer.toString((player2 + zoteBlessing2) - radianceMalediction2));
                        blessed2.setText(Integer.toString(zoteBlessing2));
                    }
                    if (player1 + player2 == falgMusicPoints) {
                        objReproductor.PauseSong();
                        String finalSong = "/Sounds/FinalMoment.mp3";
                        objReproductor.CargarMusica(finalSong);
                        objReproductor.Play();
                        FinalMoment();
                    }
                    if (player1 + player2 == maxPoints) {
                        myGridPane.setDisable(true);
                        WinnerAnno();
                    }

                } else {
                    String music = "/Voices/ParejaIncorrecta.mp3";
                    voices.CargarMusica(music);
                    voices.Play();
                    int card1Index = (firstCard.row * 4) + firstCard.column;
                    Image defaultCard = ResourceHelper.image("/CardsImages/CartaNormal.png");
                    ((ImageView) myGridPane.getChildren().get(card1Index)).setImage(defaultCard);

                    int card2Index = (secondCard.row * 4) + secondCard.column;
                    ((ImageView) myGridPane.getChildren().get(card2Index)).setImage(defaultCard);

                    if (flagPoints == true) {
                        if (malediction == true && (player1 + zoteBlessing1) - radianceMalediction1 > 0) {
                            radianceMalediction1++;
                            points1.setText(Integer.toString((player1 + zoteBlessing1) - radianceMalediction1));
                        }
                        flagPoints = false;
                    } else {
                        if (malediction == true && (player2 + zoteBlessing2) - radianceMalediction2 > 0) {
                            radianceMalediction2++;
                            points2.setText(Integer.toString((player2 + zoteBlessing2) - radianceMalediction2));
                        }
                        flagPoints = true;
                    }

                    if (flagShift == true) {
                        skin1.setDisable(true);
                        skin2.setDisable(false);
                        mssg1.setText("...");
                        mssg2.setText("¡Es mi turno!");
                        String Sound = "/Voices/RadianceZero.mp3";
                        voices.CargarMusica(Sound);
                        voices.Play();
                        flagShift = false;
                        applyBlessing2 = 0;
                        mistakes1++;
                        if (flagIA == true) {
                            PCplay();
                        }
                    } else {
                        skin1.setDisable(false);
                        skin2.setDisable(true);
                        mssg2.setText("...");
                        mssg1.setText("¡Es mi turno!");
                        String Sound = "/Voices/HornetZero.mp3";
                        voices.CargarMusica(Sound);
                        voices.Play();
                        flagShift = true;
                        applyBlessing1 = 0;
                        mistakes2++;
                    }
                }
            }
            if (secondCard != null) {
                firstCard = null;
                secondCard = null;
            }

        }
    }

    @FXML
    private void Talk1(ActionEvent event) {

        switch (voice) {
            case 1 -> {
                String Sound = "/Voices/HornetThree.mp3";
                voices.CargarMusica(Sound);
                voices.Play();
                mssg1.setText("¡No fallaré!");
                if (voice == 3) {
                    voice = 1;
                } else {
                    voice++;
                }
            }
            case 2 -> {
                String Sound = "/Voices/HornetTwo.mp3";
                voices.CargarMusica(Sound);
                voices.Play();
                mssg1.setText("¡La victoria es mía!");
                if (voice == 3) {
                    voice = 1;
                } else {
                    voice++;
                }
            }
            case 3 -> {
                String Sound = "/Voices/HornetOne.mp3";
                voices.CargarMusica(Sound);
                voices.Play();
                mssg1.setText("¡Que fácil!");
                if (voice == 3) {
                    voice = 1;
                } else {
                    voice++;
                }
            }
        }
    }

    @FXML
    private void Talk2(ActionEvent event) {

        switch (voice) {
            case 1 -> {
                String Sound = "/Voices/RadianceOne.mp3";
                voices.CargarMusica(Sound);
                voices.Play();
                mssg2.setText("¡No hay espacio para la duda!");
                if (voice == 3) {
                    voice = 1;
                } else {
                    voice++;
                }
            }
            case 2 -> {
                String Sound = "/Voices/RadianceThree.mp3";
                voices.CargarMusica(Sound);
                voices.Play();
                mssg2.setText("¡Perdiste desde que iniciaste!");
                if (voice == 3) {
                    voice = 1;
                } else {
                    voice++;
                }
            }
            case 3 -> {
                String Sound = "/Voices/RadianceTwo.mp3";
                voices.CargarMusica(Sound);
                voices.Play();
                mssg2.setText("¡Gritas demaciado!");
                if (voice == 3) {
                    voice = 1;
                } else {
                    voice++;
                }
            }
        }
    }

    @FXML
    private void RunReview(ActionEvent event) throws InterruptedException, IOException {
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/hollowmemory/Review.fxml"));
        Parent root = loader1.load();
        ReviewController controllerReview = loader1.getController();
        Scene scene1 = new Scene(root, 1500, 800);
        Stage stage1 = new Stage();
        stage1.setScene(scene1);
        controllerReview.initParamR(boardcards, row, stage1);
        stage1.setTitle("Review");
        stage1.setResizable(false);
        stage1.initStyle(StageStyle.UNDECORATED);
        stage1.show();
    }

    private void PCplay() throws FileNotFoundException {
        switch (this.amount_ofIntelligence) {
            case 1 -> {
                radiance.Easy(this.boardcards, this.row);
                String choice1 = radiance.getChoice1();
                String choice2 = radiance.getChoice2();
                SeeIAElections(choice1, choice2);

            }
            case 2 -> {
                radiance.Normal(this.boardcards, this.row);
                String choice1 = radiance.getChoice1();
                String choice2 = radiance.getChoice2();
                SeeIAElections(choice1, choice2);
            }
            case 3 -> {
                radiance.Smart(this.boardcards, this.row);
                String choice1 = radiance.getChoice1();
                String choice2 = radiance.getChoice2();
                SeeIAElections(choice1, choice2);
            }
        }

    }

    private void checkCouplesIA(int row1, int col1, int row2, int col2) throws FileNotFoundException {
        firstCard = boardcards.boardCards[row1][col1];
        secondCard = boardcards.boardCards[row2][col2];

        if (firstCard.CardImage.equals(secondCard.CardImage)) {
            boardcards.boardCards[firstCard.row][firstCard.column].cardGuessed = true;
            boardcards.boardCards[secondCard.row][secondCard.column].cardGuessed = true;
            String music = "/Voices/ParejaIncorrecta.mp3";
            voices.CargarMusica(music);
            voices.Play();
            if (blessing == true) {
                applyBlessing2++;
                if (applyBlessing2 == 2) {
                    zoteBlessing2++;
                    applyBlessing2 = 0;
                    String Sound = "/Voices/Zotee.mp3";
                    voices.CargarMusica(Sound);
                    voices.Play();
                    puntaZote2.setText("+1 PuntaZote!");
                    delay = new PauseTransition(Duration.seconds(2));
                    delay.setOnFinished(event -> {
                        puntaZote2.setText("");
                    });
                    delay.play();
                }
            }
            player2 += 2;
            couples2++;
            points2.setText(Integer.toString(player2 + zoteBlessing2));
            blessed2.setText(Integer.toString(zoteBlessing2));

            if (player1 + player2 == falgMusicPoints) {
                objReproductor.PauseSong();
                String finalSong = "/Sounds/FinalMoment.mp3";
                objReproductor.CargarMusica(finalSong);
                objReproductor.Play();
                FinalMoment();
            }
            if ((player2 + player1) < maxPoints) {
                PCplay();
            }
            if (player1 + player2 == maxPoints) {
                myGridPane.setDisable(true);
                WinnerAnno();
            }

        } else {
            String music = "/Voices/ParejaCorrecta.mp3";
            voices.CargarMusica(music);
            voices.Play();
            Image defaultCard = ResourceHelper.image("/CardsImages/CartaNormal.png");

            int card1Index = (firstCard.row * 4) + firstCard.column;
            ((ImageView) myGridPane.getChildren().get(card1Index)).setImage(defaultCard);

            int card2Index = (secondCard.row * 4) + secondCard.column;
            ((ImageView) myGridPane.getChildren().get(card2Index)).setImage(defaultCard);

            if (flagShift == false) {
                skin1.setDisable(false);
                skin2.setDisable(true);
                mssg2.setText("¡No por nada me llamo Radiance!");
                delay = new PauseTransition(Duration.seconds(2));
                delay.setOnFinished(event -> {
                    mssg2.setText("...");
                });
                delay.play();
                mssg1.setText("¡Es mi turno!");
                String Sound = "/Voices/HornetZero.mp3";
                voices.CargarMusica(Sound);
                voices.Play();
                mistakes2++;
                flagShift = true;
                flagPoints = true;
                applyBlessing1 = 0;
            }

        }
    }

    private void SeeIAElections(String choice1, String choice2) throws FileNotFoundException {

        int rowSelec1 = Integer.parseInt(choice1.split(",")[0]);
        int colSelec1 = Integer.parseInt(choice1.split(",")[1]);

        String image = boardcards.boardCards[rowSelec1][colSelec1].CardImage;
        Image selecImage = ResourceHelper.image("/CardsImages/" + image + ".png");

        int card1Index = (rowSelec1 * 4) + colSelec1;
        ((ImageView) myGridPane.getChildren().get(card1Index)).setImage(selecImage);

        int rowSelec2 = Integer.parseInt(choice2.split(",")[0]);
        int colSelec2 = Integer.parseInt(choice2.split(",")[1]);

        image = boardcards.boardCards[rowSelec2][colSelec2].CardImage;
        selecImage = ResourceHelper.image("/CardsImages/" + image + ".png");

        int card2Index = (rowSelec2 * 4) + colSelec2;
        ((ImageView) myGridPane.getChildren().get(card2Index)).setImage(selecImage);

        checkCouplesIA(rowSelec1, colSelec1, rowSelec2, colSelec2);
    }

    private void WinnerAnno() throws FileNotFoundException {
        if ((player1 + zoteBlessing1) - radianceMalediction1 > (player2 + zoteBlessing2) - radianceMalediction2) {
            winner.setText("¡La victoria es de " + namePlayer1.getText() + "!");
            winner.setDisable(false);
            Resume();
        } else if ((player2 + zoteBlessing2) - radianceMalediction2 > (player1 + zoteBlessing1) - radianceMalediction1) {
            winner.setText("¡La victoria es de " + namePlayer2.getText() + "!");
            winner.setDisable(false);
            Resume();
        } else {
            winner.setText("¡Empate!");
            winner.setDisable(false);
            Resume();
        }
        this.timeline.stop();
        seconds.setText("00");
        minutes.setText("00");
        WriteWiner();
    }

    private void PartyTime() {
        this.timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {

            if (this.seg == 0 && this.min >= 0) {
                this.min--;
                minutes.setText(Integer.toString(min));
                this.seg = 60;
            }
            if (this.min >= 0) {
                this.seg--;
            }
            seconds.setText(Integer.toString(seg));
            if (this.min == 0 && this.seg == 0) {
                this.min--;
            }
            if (this.min < 0 && this.seg == 0) {
                myGridPane.setDisable(true);
                try {
                    WinnerAnno();
                } catch (FileNotFoundException ex) {
                    System.out.println("Error al llamar al metodo WinnerAnno");
                }
            }
        }));
        this.timeline.setCycleCount(Animation.INDEFINITE);
        this.timeline.play();
    }

    private void Resume() throws FileNotFoundException {
        Image finalWall = ResourceHelper.image("/Extras/Shadow.png");
        shadow.setImage(finalWall);
        shadow.setDisable(false);
        lbl_InfoPlayer1.setText("*" + namePlayer1.getText() + "*\n" + "\nPuntos obtenidos: "
                + points1.getText() + "\nParejas encontradas: " + Integer.toString(couples1)
                + "\nPuntaZotes: x" + blessed1.getText() + "\nIntentos fallidos: " + Integer.toString(mistakes1));
        lbl_InfoPlayer1.setDisable(false);
        lbl_InfoPlayer2.setText("*" + namePlayer2.getText() + "*\n" + "\nPuntos obtenidos: "
                + points2.getText() + "\nParejas encontradas: " + Integer.toString(couples2)
                + "\nPuntaZotes: x" + blessed2.getText() + "\nIntentos fallidos: " + Integer.toString(mistakes2));
        lbl_InfoPlayer2.setDisable(false);
    }

    private void FinalMoment() throws FileNotFoundException {
        Image flash = ResourceHelper.image("/Extras/flash.png");
        shadow.setImage(flash);
        shadow.setDisable(false);
        delay = new PauseTransition(Duration.seconds(0.1));
        delay.setOnFinished(event -> {
            try {
                Image flash2 = ResourceHelper.image("/Extras/nada.png");
                shadow.setImage(flash2);
                shadow.setDisable(true);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(TableroController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        delay.play();
        delay = new PauseTransition(Duration.seconds(0.2));
        delay.setOnFinished(evet -> {
            try {
                Image flash2 = ResourceHelper.image("/Extras/flash.png");
                shadow.setImage(flash2);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(TableroController.class.getName()).log(Level.SEVERE, null, ex);
            }

            shadow.setDisable(false);
        });
        delay.play();
        delay = new PauseTransition(Duration.seconds(0.4));
        delay.setOnFinished(event -> {
            try {
                Image flash2 = ResourceHelper.image("/Extras/nada.png");
                shadow.setImage(flash2);
                shadow.setDisable(true);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(TableroController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        delay.play();

        Image finalBoard = ResourceHelper.image("/Fondos/TableroFinal.png");
        tablero.setImage(finalBoard);
    }

    private void WriteWiner() {
        String victorious = "", pointsWinner = "", difficulty = "", opponent = "";
        if ((player1 + zoteBlessing1) - radianceMalediction1 > (player2 + zoteBlessing2) - radianceMalediction2) {
            victorious = namePlayer1.getText();
            pointsWinner = Integer.toString(player1);

            switch (amount_ofIntelligence) {
                case 1 -> {
                    difficulty = "Facil";
                    break;
                }
                case 2 -> {
                    difficulty = "Normal";
                    break;
                }
                case 3 -> {
                    difficulty = "Dificil";
                    break;
                }
            }
        } else {
            victorious = namePlayer2.getText();
            pointsWinner = Integer.toString(player2);

            switch (amount_ofIntelligence) {
                case 1 -> {
                    difficulty = "Facil";
                    break;
                }
                case 2 -> {
                    difficulty = "Normal";
                    break;
                }
                case 3 -> {
                    difficulty = "Dificil";
                    break;
                }
            }
        }
        if (flagIA == true) {
            opponent = "PC";
        } else {
            opponent = "Jc";
        }
        if ((player1 + zoteBlessing1) - radianceMalediction1 != (player2 + zoteBlessing2) - radianceMalediction2) {
            try {
                HistoryStore.append("Ganador: " + victorious + "   | Punto: " + pointsWinner + "   | Dificultad: " + difficulty + " | Jc VS " + opponent + ".");
            } catch (IOException e) {
                System.err.println("Error al escribir en el historial: " + e.getMessage());
            }
        } else {
            try {
                HistoryStore.append("Empate! | " + "Jugadores: " + namePlayer1.getText() + " & " + namePlayer2.getText() + "   | Dificultad: " + difficulty + " | Jc VS " + opponent + ".");
            } catch (IOException e) {
                System.err.println("Error al escribir en el historial: " + e.getMessage());
            }
        }

    }
}
