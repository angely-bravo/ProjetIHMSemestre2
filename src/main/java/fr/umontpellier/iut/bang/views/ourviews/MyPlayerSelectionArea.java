package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.ICard;
import fr.umontpellier.iut.bang.IGame;
import fr.umontpellier.iut.bang.IPlayer;
import fr.umontpellier.iut.bang.views.CardView;
import fr.umontpellier.iut.bang.views.PlayerArea;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import fr.umontpellier.iut.bang.views.PlayerSelectionArea;

public class MyPlayerSelectionArea extends PlayerSelectionArea {

    public MyPlayerSelectionArea(PlayerArea playerArea) {
        super(playerArea);
        this.setOnMouseClicked(event -> setPlayerSelectedListener());
    }

    @Override
    public void setVisible() {

    }

    @Override
    public void setUnVisible() {

    }

    @Override
    protected void setPlayerSelectedListener() {
        getPlayerArea().getGameView().getIGame().onTargetSelection(this.getPlayerArea().getIPlayer());
    }


}
