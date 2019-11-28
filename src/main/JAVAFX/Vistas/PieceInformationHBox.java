package Vistas;

import Controlers.ChangeInformationOnLabelEventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;

public class PieceInformationHBox extends HBox {


    public PieceInformationHBox ( TextFlow label, String cost, String life, String damage, String behaviour ) {
        Button costButton = new Button ( "Cost" );
        costButton.setOnAction ( new ChangeInformationOnLabelEventHandler ( label, costButton, cost ) );

        Button lifeButton = new Button ( "Life" );
        lifeButton.setOnAction ( new ChangeInformationOnLabelEventHandler ( label, lifeButton, life ) );

        Button damageButton = new Button ( "Damage" );
        damageButton.setOnAction ( new ChangeInformationOnLabelEventHandler ( label, damageButton, damage ) );

        Button behaviourButton = new Button ( "Behaviour" );
        behaviourButton.setOnAction ( new ChangeInformationOnLabelEventHandler ( label, behaviourButton, behaviour ) );

        this.getChildren ().addAll ( costButton,lifeButton,damageButton,behaviourButton );
        this.setSpacing ( 10 );
        this.setAlignment ( Pos.CENTER );

    }

}