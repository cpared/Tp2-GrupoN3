package Controlers.PieceStatHandlers;

import Vistas.SelectPieceSceneView;
import boardFx.ButtonPiece;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class RiderSelectStatsHandler implements EventHandler<InputEvent>{

    private SelectPieceSceneView scene;
    private final ButtonPiece button;
    private String attack = "15 / 5";
    private String cost = "3";
    private String health = "100";
    private String info = "- If there is at least one ally Soldier near by or any enemy, his weapon are arch and arrow \n - If there are enemies close and no ally pieces, his weapon is a sword";
    private Label attackInfo;
    private Label healthInfo;
    private Label priceInfo;
    private Label pieceInfo;
    private ImageView attackImageChange;

    public RiderSelectStatsHandler(Label attackInformation, Label healthInformation, Label priceInformation, Label information, ImageView attackImage, SelectPieceSceneView scene, ButtonPiece rider){
        this.attackInfo =  attackInformation;
        this.healthInfo = healthInformation;
        this.priceInfo = priceInformation;
        this.pieceInfo = information;
        this.attackImageChange = attackImage;
        this.scene = scene;
        this.button = rider;
    }

    @Override
    public void handle(InputEvent inputEvent) {
        if (inputEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
            this.attackInfo.setText(this.attack);
            this.attackInfo.setTextFill(Color.WHITE);
            this.healthInfo.setText(this.health);
            this.healthInfo.setTextFill(Color.WHITE);
            this.priceInfo.setText(this.cost);
            this.priceInfo.setTextFill(Color.WHITE);
            this.pieceInfo.setText(this.info);
            this.pieceInfo.setTextFill(Color.WHITE);

            Image url = new Image("Image/piercing-sword.png");
            this.attackImageChange.setImage(url);

            scene.setLastClicked(button);

        }
    }

}


