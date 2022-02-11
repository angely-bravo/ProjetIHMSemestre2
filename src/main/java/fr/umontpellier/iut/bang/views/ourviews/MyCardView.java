package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.ICard;
import fr.umontpellier.iut.bang.IGame;
import fr.umontpellier.iut.bang.IPlayer;
import fr.umontpellier.iut.bang.views.CardView;
import fr.umontpellier.iut.bang.views.PlayerArea;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class MyCardView extends CardView {

    public MyCardView(ICard card, PlayerArea playerArea) {
        super(card, playerArea);
        Label laCarte = new Label(card.getName());
        Image chou;
        /*if(playerArea.getPlayer().getGame().getCurrentPlayer().equals(playerArea.getPlayer())) {
            chou = new Image(card.getCard().getImageName(), 50, 75, false, false);
        }
        else{
            chou = new Image("src/main/resources/images/cards/back.png",50,75,false,false);
        }*/
        chou = new Image(card.getCard().getImageName(), 50, 75, false, false);
        ImageView crak = new ImageView(chou);
        crak.setY(crak.getY()+25);
        getChildren().add(crak);
        getChildren().add(laCarte);
        setCardSelectionListener();
    }

    @Override
    public void setVisible() {

    }

    @Override
    public void setUnVisible() {

    }

    @Override
    protected void setCardSelectionListener() {
        setOnMouseClicked(whenCardSelected);
    }

    private EventHandler<MouseEvent> whenCardSelected = mouseEvent -> {
        CardView selectedCardView =  (CardView) mouseEvent.getSource();
        ICard selectedCard = selectedCardView.getICard();
        IGame currentGame = selectedCardView.getPlayerArea().getGameView().getIGame();
        IPlayer targetPlayer = selectedCardView.getPlayerArea().getIPlayer();
        currentGame.onCardSelection(selectedCard, targetPlayer);
    };
}
