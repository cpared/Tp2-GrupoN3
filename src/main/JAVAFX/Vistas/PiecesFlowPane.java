package Vistas;

import Controlers.PieceButtonEventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;


public class PiecesFlowPane extends TilePane {

    public PiecesFlowPane (String background, ChoosingPiecesBorderPane choosingPiecesBorderPane) {
        this.setAlignment ( Pos.CENTER );
        this.setPrefColumns ( 1 );
        this.setHgap ( 30 );
        this.setVgap ( 30 );
        this.setPrefRows ( 4 );
        this.setStyle ( background );
        // rojo : DC143C
        // rojo ladrillo: B22222
        // crema: FFFAF0
        // arena: DEB887
        Button pages[] = new Button[4];

        //Creating the images
        ImageView soldier = new ImageView ( new Image ( "Image/pieces/soldier.png" ) );
        soldier.setFitHeight ( 150 );
        soldier.setFitWidth ( 150 );
        ImageView healer = new ImageView ( new Image ( "Image/pieces/healer.png" ) );
        healer.setFitHeight ( 150 );
        healer.setFitWidth ( 150 );
        ImageView rider = new ImageView ( new Image ( "Image/pieces/rider.png" ) );
        rider.setFitHeight ( 150 );
        rider.setFitWidth ( 150 );
        ImageView catapult = new ImageView ( new Image ( "Image/pieces/catapult2.png" ) );
        catapult.setFitHeight ( 150 );
        catapult.setFitWidth ( 150 );

        Button soldierButton = new Button ();
        soldierButton.setGraphic ( soldier );
        soldierButton.setOnMouseClicked ( new PieceButtonEventHandler ( choosingPiecesBorderPane, background ,"The soldier cost is: 1.","100","Soldier can only attack when in close range with another piece. The damage it causes is: 10","If 3 soldiers are on the same row, they can form a Battalion (the three soldiers move together in the same direction)",new Image ( "Image/pieces/soldier.png" ) ) );
        soldierButton.setOnKeyPressed ( new PieceButtonEventHandler ( choosingPiecesBorderPane,  background ,"The soldier cost is: 1.","100","Soldier can only attack when in close range with another piece. The damage it causes is: 10","If 3 soldiers are on the same row, they can form a Battalion (the three soldiers move together in the same direction)" ,new Image ( "Image/pieces/soldier.png" ) ) );

        Button healerButton = new Button ();
        healerButton.setGraphic ( healer );
        healerButton.setOnMouseClicked ( new PieceButtonEventHandler ( choosingPiecesBorderPane,  background ,"The healer cost is: 2.","75","Healer cant damage other pieces. It can heal allies and give them 15 points of thir life back","It can heal one allie within a close range. Catapult can't be healed.",new Image ( "Image/pieces/healer.png" ) ));
        healerButton.setOnKeyPressed ( new PieceButtonEventHandler ( choosingPiecesBorderPane,  background ,"The healer cost is: 2.","75","Healer cant damage other pieces. It can heal allies and give them 15 points of thir life back","It can heal one allie within a close range. Catapult can't be healed." ,new Image ( "Image/pieces/healer.png" ) ));


        Button riderButton = new Button ();
        riderButton.setGraphic ( rider );
        riderButton.setOnMouseClicked ( new PieceButtonEventHandler ( choosingPiecesBorderPane, background ,"The rider cost is: 3.","100","The rider can attack both in close range and in long range. Close range attacks take 5 life points. Long distance attacks take 15 life points.","If there is a Soldier in close range or there are no enemies in close rage, it attacks with a long range attack. If there are no allies and there are enemies nearby, it can only attack in close range.",new Image ( "Image/pieces/rider.png" ) ));
        riderButton.setOnKeyPressed ( new PieceButtonEventHandler ( choosingPiecesBorderPane, background ,"The rider cost is: 3.","100","The rider can attack both in close range and in long range. Close range attacks take 5 life points. Long distance attacks take 15 life points.","If there is a Soldier in close range or there are no enemies in close rage, it attacks with a long range attack. If there are no allies and there are enemies nearby, it can only attack in close range." ,new Image ( "Image/pieces/rider.png" ) ));


        Button catapultButton = new Button ();
        catapultButton.setGraphic ( catapult );
        catapultButton.setOnMouseClicked ( new PieceButtonEventHandler ( choosingPiecesBorderPane,  background ,"The rider cost is: 5.","50","The catapult only has long range attacks. These attacks take 20 points drom the opponents lives.","It can't move at all during the game. The attacks the catapult launches can hurt both allies and enemies. It causes damage on the first piece that it finds and the damage spreads to the adjoining pieces, causing the same damage on each piece." ,new Image ( "Image/pieces/catapult2.png" ) ));
        catapultButton.setOnKeyPressed ( new PieceButtonEventHandler ( choosingPiecesBorderPane,  background ,"The rider cost is: 5.","50","The catapult only has long range attacks. These attacks take 20 points drom the opponents lives.","It can't move at all during the game. The attacks the catapult launches can hurt both allies and enemies. It causes damage on the first piece that it finds and the damage spreads to the adjoining pieces, causing the same damage on each piece." ,new Image ( "Image/pieces/catapult2.png" ) ));


        // Adding it.
        pages[0] = soldierButton;
        pages[1] = healerButton;
        pages[2] = riderButton;
        pages[3] = catapultButton;

        this.getChildren ().add ( pages[0] );
        this.getChildren ().add ( pages[1] );
        this.getChildren ().add ( pages[2] );
        this.getChildren ().add ( pages[3] );
    }


}
