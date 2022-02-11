package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.IGame;
import fr.umontpellier.iut.bang.IPlayer;
import fr.umontpellier.iut.bang.logic.Player;
import fr.umontpellier.iut.bang.logic.cards.Card;
import fr.umontpellier.iut.bang.views.GameView;
import fr.umontpellier.iut.bang.views.PlayerArea;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

import java.util.Collections;

public class MyGameView extends GameView {

    private Pane vueDesJoueurs;
    private Button pass;
    private Pane vueGlobale;
    private Pane vueJeu;


    public MyGameView(IGame game) {
        super(game);
        int i =0;
        vueDesJoueurs = new Pane();
        for(Player p : game.getPlayers()){

            if(i==0){
               MyPlayerArea jof = new MyPlayerArea(new IPlayer(p),this);
               jof.setLayoutX(575);
               jof.setLayoutY(650);
               vueDesJoueurs.getChildren().add(jof);
            }
            else if(i==1){
                MyPlayerArea ka = new MyPlayerArea(new IPlayer(p),this);
                ka.setLayoutX(0);
                ka.setLayoutY(300);
                vueDesJoueurs.getChildren().add(ka);
            } else if(i==2){
                MyPlayerArea pa = new MyPlayerArea(new IPlayer(p),this);
                pa.setLayoutX(575);
                pa.setLayoutY(0);
                vueDesJoueurs.getChildren().add(pa);
            } else if(i==3){
                MyPlayerArea pog = new MyPlayerArea(new IPlayer(p),this);
                pog.setLayoutX(1200);
                pog.setLayoutY(300);
                vueDesJoueurs.getChildren().add(pog);
            }
            i++;
        }
        pass = new Button("pass");
        pass.setLayoutX(725);
        pass.setLayoutY(455);

        ImageView sus = new ImageView("src/main/resources/images/cards/back.png");
        sus.setFitHeight(100);
        sus.setFitWidth(60);
        sus.setLayoutX(600);
        sus.setLayoutY(425);
        vueDesJoueurs.getChildren().add(sus);
        vueDesJoueurs.getChildren().add(pass);

        vueGlobale=new Pane();

        vueJeu=new Pane();


        vueGlobale.getChildren().add(vueDesJoueurs);
        vueGlobale.getChildren().add(vueJeu);
        vueGlobale.setBackground(
                new Background(
                        Collections.singletonList(new BackgroundFill(
                                Color.WHITE,
                                new CornerRadii(0),
                                new Insets(0))),
                        Collections.singletonList(new BackgroundImage(
                                new Image("src/main/resources/images/wood.jpg", 3000, 3000, false, false),
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundPosition.DEFAULT,
                                new BackgroundSize(1500, 1500, false, false, false, false)
                        ))));





        getChildren().add(vueGlobale);
        setCurrentPlayerChangesListener(whenCurrentPlayerChanges);
        setPassSelectedListener();
        getIGame().run();
    }


    private ChangeListener<? super Player> whenCurrentPlayerChanges = new ChangeListener<Player>() {
        @Override
        public void changed(ObservableValue<? extends Player> observableValue, Player player, Player t1) {
            if(player != null){
                findPlayerArea(player).deHightlightCurrentArea();
            }
            findPlayerArea(t1).highlightCurrentArea();
        }
    };

    @Override
    protected void bindNextActionMessage() {

    }

    @Override
    protected void setPassSelectedListener() {
        pass.setOnAction(event -> { getIGame().onPass(); });
    }

    private PlayerArea findPlayerArea(Player player) {
        for (Node n : vueDesJoueurs.getChildren()) {
            PlayerArea nodePlayerArea = (PlayerArea) n;
            Player nodePlayer = nodePlayerArea.getPlayer();
            if (nodePlayer.equals(player))
                return nodePlayerArea;
        }
        return null;
    }

   /* private PlayerArea findTargetPlayerArea(Player player) {
        for (Node n : vueDesJoueurs.getChildren()) {
            PlayerArea nodePlayerArea = (PlayerArea) n;
            Player nodePlayer = nodePlayerArea.getPlayer();
            if (nodePlayer.getGame().isPossibleTarget(nodePlayer))
                return nodePlayerArea;
        }
        return null;
    }*/
}
