package hollowmemory;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author david
 */
public class Reproductor {
    //atributos
    private File musicPacth;
    private Media sound;
    private MediaPlayer player;
    //metodos
    public void CargarMusica(String music){
    try
        {
            this.musicPacth = new File(music);
            
            if(this.musicPacth.exists()){
                  this.sound = new Media(new File(music).toURI().toString());
                    this.player = new MediaPlayer(sound);
                    this.player.setVolume(0.30);
            }
            else
            {
                System.out.println("Archivo no encontrado");
            }
        }
        catch(Exception ex)
        {
            System.out.println("Archivo no encontrado");
        }
    }
    
    public void Play(){
     this.player.play();
    }
    public void PauseSong(){
    this.player.pause();
    };
}

