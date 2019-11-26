package Vistas;


import Controlers.GeneralButtonEvents.MouseIsOffTheButtonEventHandler;
import Controlers.GeneralButtonEvents.MouseIsOnTheButtonEventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ButtonView extends Button {
    private String borderColor;
    private String borderWidth;
    private String backgroundColor;
    private String textColor;
    private String fontSize;


    public ButtonView () {
        this.borderColor = "-fx-border-color: #fd3d3d3";
        this.borderWidth = "-fx-border-width: 5px";
        this.backgroundColor = "-fx-background-color: #b0c4de,linear-gradient(#fafdfe, #e8f5fc),linear-gradient(#eaf6fd 0%, #d9f0fc 49%, #bee6fd 50%, #a7d9f5 100%)";
        this.textColor = "-fx-text-fill: #000000";
        this.fontSize = "-fx-font-size:40";
    }

    private void decorate ( Button button ) {
        //button.setStyle ( this.borderColor +";"+ borderWidth +";"+ this.backgroundColor +";"+ this.textColor );
        button.setStyle ( "-fx-padding: 8 15 15 15;-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;-fx-background-radius: 8;-fx-background-color: linear-gradient(from 0% 93% to 0% 100%, #3f58a3 0%, #164d90 100%),#034b9d,#168ad8,radial-gradient(center 50% 50%, radius 100%, #168ad8, #034b9d);-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );-fx-font-weight: bold;-fx-font-size: 1.1em;" );

    }

    public Button createButton ( String buttonText ) {
        Button button = new Button ( buttonText );
        button.setFont ( Font.font ( "Modern No.20",15 ) );
        //si quisiera ser redondo.
        //button.setShape ( new Circle (100) );
        button.addEventHandler ( MouseEvent.MOUSE_ENTERED, new MouseIsOnTheButtonEventHandler ( button ) );
        button.addEventHandler ( MouseEvent.MOUSE_EXITED, new MouseIsOffTheButtonEventHandler ( button ) );
        this.decorate ( button );
        return button;
    }

}
