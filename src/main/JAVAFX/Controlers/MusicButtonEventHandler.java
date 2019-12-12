package Controlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.media.MediaPlayer;

public class MusicButtonEventHandler implements EventHandler<ActionEvent> {
    private MediaPlayer mediaPlayer;
    private Button stopButton;
    private int count;

    public MusicButtonEventHandler (MediaPlayer mediaPlayer, Button stopButton) {
        this.mediaPlayer = mediaPlayer;
        this.stopButton = stopButton;
        this.count=0;
    }

    @Override
    public void handle ( ActionEvent actionEvent ) {
        count++;
        if (count%2==0 ) {
            mediaPlayer.setMute ( false );
            mediaPlayer.play ();
            stopButton.getStyleClass ().removeAll ( "buttonStopMuted" );
            stopButton.getStyleClass ().add ( "buttonStop" );
        } else if (count%2!=0 ) {
            mediaPlayer.setMute ( true );
            stopButton.getStyleClass ().add ( "buttonStopMuted" );
        }

    }
}
