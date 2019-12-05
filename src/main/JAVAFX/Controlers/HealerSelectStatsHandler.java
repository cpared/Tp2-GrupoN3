package Controlers;

import boardFx.ButtonPiece;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.net.URL;

public class HealerSelectStatsHandler implements EventHandler<InputEvent>{

    private ButtonPiece clicked;
    private final ButtonPiece button;
    private String attack = "15";
        private String cost = "2";
        private String health = "75";
        private String info = "Lorem";
        private Label attackInfo;
        private Label healthInfo;
        private Label priceInfo;
        private Label pieceInfo;
        private ImageView heal;

        public HealerSelectStatsHandler(Label attackInformation, Label healthInformation, Label priceInformation, Label information, ImageView healImage, ButtonPiece clicked, ButtonPiece healer){
            this.attackInfo =  attackInformation;
            this.healthInfo = healthInformation;
            this.priceInfo = priceInformation;
            this.pieceInfo = information;
            this.heal = healImage;
            this.clicked = clicked;
            this.button = healer;
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

                Image url = new Image("Image/health-increase.png");
                this.heal.setImage(url);

                this.clicked = this.button;
            }
        }

}
