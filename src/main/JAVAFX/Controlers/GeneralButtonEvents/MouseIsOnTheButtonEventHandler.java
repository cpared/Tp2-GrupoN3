package Controlers.GeneralButtonEvents;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class MouseIsOnTheButtonEventHandler implements EventHandler<MouseEvent> {
    private Button button;

    public MouseIsOnTheButtonEventHandler (Button button) {
        this.button = button;
    }

    @Override
    public void handle ( MouseEvent event ) {
        if (event.getEventType () == MouseEvent.MOUSE_CLICKED || event.getEventType () == MouseEvent.MOUSE_PRESSED){
            System.out.println ( "click" );
            InnerShadow inner = new InnerShadow ();
            this.button.setEffect(inner);
            this.button.fireEvent ( new ActionEvent (  ) );
        }

        if (event.getEventType () == MouseEvent.MOUSE_ENTERED){
            System.out.println ( "ebtro" );
            DropShadow shadow = new DropShadow ();
            shadow.setColor ( Color.BLACK );
            this.button.setEffect(shadow);
        }
        if (event.getEventType () == MouseEvent.MOUSE_EXITED) {
            System.out.println ( "salio" );
            this.button.setEffect(null);
        }


    }
}
