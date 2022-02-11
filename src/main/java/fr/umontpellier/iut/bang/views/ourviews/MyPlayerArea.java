package fr.umontpellier.iut.bang.views.ourviews;

import fr.umontpellier.iut.bang.ICard;
import fr.umontpellier.iut.bang.IPlayer;
import fr.umontpellier.iut.bang.logic.Player;
import fr.umontpellier.iut.bang.logic.cards.*;
import fr.umontpellier.iut.bang.views.CardView;
import fr.umontpellier.iut.bang.views.GameView;
import fr.umontpellier.iut.bang.views.PlayerArea;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MyPlayerArea extends PlayerArea {

    private HBox laMain;
    private HBox inPlay;
   // private HBox targets;
    private VBox zoneJoueur;
    private VBox inPlayArea;
    //private VBox targetsArea;
    private VBox charView;
    private Card selectedCard;
    private IPlayer selectedplayer;

    public MyPlayerArea(IPlayer player, GameView gameView) {
        super(player, gameView);
        zoneJoueur = new VBox();
        Label nom = new Label(player.getPlayer().getName());

        /**
         * La main
         */
        laMain = new HBox();

        /**
         * Bang Character Info
         */
/**
 *      InPlay (label+image)
 */
        inPlayArea = new VBox();
        inPlay = new HBox();
        Label ineplay = new Label("En jeu :");
        inPlayArea.getChildren().add(ineplay);
        inPlayArea.getChildren().add(inPlay);

/**
 *      Les balles en guise de vie
 */
        HBox hp = new HBox();
        for (int i = 0; i < player.getPlayer().getHealthPoints(); i++) {
            Image bulletPng = new Image("src/main/resources/images/bullet_v.png", 25, 50, false, false);
            ImageView bulletImgView = new ImageView(bulletPng);
            hp.getChildren().add(bulletImgView);
        }

/**
 *      Image du personnage+role
 */
        HBox infoJoueur = new HBox();
        Image charImg = new Image("images/roles/" + player.getPlayer().getRole().toString().toLowerCase().replaceAll("[. !]+", "") + ".png", 50, 75, false, false);
        ImageView charImgView = new ImageView(charImg);
        Image role = new Image(player.getBangCharacter().getImageName(), 50, 75, false, false);
        ImageView roleview = new ImageView(role);
       // infoJoueur.getChildren().add(charImgView);
        //infoJoueur.getChildren().add(roleview);

        charView = new VBox();
        charView.getChildren().add(hp);
        //charView.getChildren().add(infoJoueur);
        charView.getChildren().add(charImgView);
        charView.getChildren().add(roleview);


        /**
         * Adding zone
         */
        charView.getChildren().add(nom);
//        zoneJoueur.getChildren().add(charView);
        MyPlayerSelectionArea selektion = new MyPlayerSelectionArea(this);
        selektion.getChildren().add(charView);
        zoneJoueur.getChildren().add(selektion);
        zoneJoueur.getChildren().add(infoJoueur);

        zoneJoueur.getChildren().add(laMain);
        zoneJoueur.getChildren().add(inPlayArea);
        getChildren().add(zoneJoueur);
        setHandListener(whenUpdateHand);
        setHealthPointsListener(whenHealthUpdate);
        setInPlayListener(whenUpdateInPlay);

      /*    charView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                System.out.println("TEST");
                event.consume();
            }
        });*/



    }


    EventHandler<MouseEvent> cardHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            selectedCard = (Card) event.getSource();
            event.consume();
        }
    };
    EventHandler<MouseEvent> charViewHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            selectedplayer = (IPlayer) event.getSource();
            event.consume();
        }
    };


    private ListChangeListener<Card> whenUpdateHand = new ListChangeListener<Card>() {
        @Override
        public void onChanged(Change<? extends Card> change) {
            while (change.next()){
                if(change.wasAdded()){
                    for (Card c : change.getAddedSubList()){
                        laMain.getChildren().add(new MyCardView(new ICard(c), MyPlayerArea.this));
                    }
                }else if(change.wasRemoved()){
                    for (Card card : change.getRemoved()){
                        laMain.getChildren().remove(findCardView(card, laMain));
                        if (card.expectsReaction()){
//                                getGameView().getIGame().onTargetSelection(selectedplayer);
                                getGameView().getIGame().onCardSelection(new ICard(card),selectedplayer);

//                                card.onTargetSelection(getIPlayer().getPlayer());
                        }

                    }
                }
            }
        }
    };

    private ListChangeListener<BlueCard> whenUpdateInPlay = new ListChangeListener<BlueCard>() {
        @Override
        public void onChanged(Change<? extends BlueCard> change) {
            while (change.next()){
                if(change.wasAdded()){
                    for (Card c : change.getAddedSubList()){
                        inPlay.getChildren().add(new MyCardView(new ICard(c), MyPlayerArea.this));
                    }
                }else if(change.wasRemoved()){
                    for (Card card : change.getRemoved()){
                        inPlay.getChildren().remove(findCardView(card, inPlay));
                    }
                }
            }
        }
    };

    //Update des points de vie

    private ChangeListener<? super Number> whenHealthUpdate = new ChangeListener<Number>() {
        @Override
        public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
            charView.getChildren().remove(0);
            HBox LUBULULE = new HBox();
            for(int i=0;i<getPlayer().getHealthPoints();i++){
                Image pouet = new Image("src/main/resources/images/bullet_v.png",25,50,false,false);
                ImageView cacahuete = new ImageView(pouet);
                LUBULULE.getChildren().add(cacahuete);
            }
            for(int i=0;i<getPlayer().getHealthPointsMax()-getPlayer().getHealthPoints();i++) {
                Image pouet = new Image("src/main/resources/images/bullet_grey_v.png", 25, 50, false, false);
                ImageView cacahuete = new ImageView(pouet);
                LUBULULE.getChildren().add(cacahuete);
            }
            charView.getChildren().add(0,LUBULULE);
        }
    };



    private CardView findCardView(Card cardtofind, HBox laMain){
        for(Node n: laMain.getChildren()){
            CardView cv = (CardView) n;
            Card c = cv.getCard();
            if(c.equals(cardtofind)){
                return cv;
            }
        }
        return null;
    }

    @Override
    public void highlightCurrentArea() {
        setStyle("-fx-background-color: lightblue");
    }

    @Override
    public void deHightlightCurrentArea() {
        setStyle("-fx-background-color: transparent");
    }




}
