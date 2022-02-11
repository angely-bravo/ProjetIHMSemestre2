package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.ICard;
import fr.umontpellier.iut.bang.IGame;
import fr.umontpellier.iut.bang.IPlayer;
import fr.umontpellier.iut.bang.views.BangCharacterView;
import fr.umontpellier.iut.bang.views.CardView;
import fr.umontpellier.iut.bang.views.PlayerArea;
import fr.umontpellier.iut.bang.views.ourviews.MyPlayerArea;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


public class MyBangCharacterView extends BangCharacterView {

    public MyBangCharacterView(PlayerArea playerArea) {
        super(playerArea);
        Image chouRouge;

        chouRouge = new Image("https://cdn.discordapp.com/attachments/767833655782408202/853149991039926272/81vw7XvSJeL.png");
        ImageView coke = new ImageView(chouRouge);
        coke.setY(coke.getY()+25);
        getChildren().add(coke);

        setBangCharacterSelectionListener();
        System.out.println("Hector le castor");

    }


    @Override
    public void setVisible() {

    }

    @Override
    public void setUnVisible() {

    }

    @Override
    protected void setBangCharacterSelectionListener() {
        setOnMouseClicked(whenBangCharacterSelected);
    }


    private EventHandler<MouseEvent> whenBangCharacterSelected = mouseEvent -> {
        BangCharacterView selectedBangCharacterView =  (BangCharacterView) mouseEvent.getSource();
        IGame currentGame = selectedBangCharacterView.getPlayerArea().getGameView().getIGame();
        IPlayer targetPlayer = selectedBangCharacterView.getPlayerArea().getIPlayer();
        currentGame.onTargetSelection(targetPlayer);
    };


}
