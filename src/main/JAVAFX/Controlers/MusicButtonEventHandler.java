package Controlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.media.MediaPlayer;

public class MusicButtonEventHandler implements EventHandler<ActionEvent> {
    private MediaPlayer mediaPlayer;
    private Button stopButton;
    private int count;
    private String onStyle;
    private String offStyle;

    public MusicButtonEventHandler (MediaPlayer mediaPlayer, Button stopButton, String onStyle, String offStyle) {
        this.mediaPlayer = mediaPlayer;
        this.stopButton = stopButton;
        this.count=0;
        this.onStyle= onStyle;
        this.offStyle = offStyle;
    }

    @Override
    public void handle ( ActionEvent actionEvent ) {
        count++;
        if (count%2==0 ) {
            mediaPlayer.setMute ( false );
            mediaPlayer.play ();
            stopButton.getStyleClass ().removeAll ( this.offStyle );
            stopButton.getStyleClass ().add ( this.onStyle );
        } else if (count%2!=0 ) {
            mediaPlayer.setMute ( true );
            stopButton.getStyleClass ().add ( this.offStyle );
        }

    }
}
