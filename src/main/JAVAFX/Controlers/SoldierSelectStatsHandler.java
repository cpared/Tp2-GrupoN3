package Controlers;

import Vistas.SelectPieceSceneView;
import boardFx.ButtonPiece;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class SoldierSelectStatsHandler implements EventHandler<InputEvent>{
    private SelectPieceSceneView scene;
    private final ButtonPiece button;
    private String attack = "10";
    private String cost = "1";
    private String health = "100";
    private String info = "Lorem";
    private Label attackInfo;
    private Label healthInfo;
    private Label priceInfo;
    private Label pieceInfo;
    private ImageView attackImageChange;

    public SoldierSelectStatsHandler(Label attackInformation, Label healthInformation, Label priceInformation, Label information, ImageView attackImage, SelectPieceSceneView scene, ButtonPiece soldierButton){
        this.attackInfo =  attackInformation;
        this.healthInfo = healthInformation;
        this.priceInfo = priceInformation;
        this.pieceInfo = information;
        this.attackImageChange = attackImage;
        this.scene = scene;
        this.button = soldierButton;
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
            scene.setLastClicked(button);
            Image url = new Image("Image/broadsword.png");
            this.attackImageChange.setImage(url);
        }
    }

}
