package hollowmemory;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author david
 */
public class Reproductor {
    //atributos
    private Media sound;
    private MediaPlayer player;
    //metodos
    public void CargarMusica(String music){
        try {
            this.sound = new Media(ResourceHelper.resource(music).toExternalForm());
            this.player = new MediaPlayer(sound);
            this.player.setVolume(0.30);
        } catch (Exception ex) {
            this.player = null;
            System.out.println("Archivo no encontrado");
        }
    }
    
    public void Play(){
        if (this.player != null) {
            this.player.play();
        }
    }
    public void PauseSong(){
        if (this.player != null) {
            this.player.pause();
        }
    }
}

