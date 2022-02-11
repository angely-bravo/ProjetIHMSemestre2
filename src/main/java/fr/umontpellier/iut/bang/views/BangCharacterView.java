package fr.umontpellier.iut.bang.views;

import fr.umontpellier.iut.bang.ICard;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class BangCharacterView extends Pane {
    private PlayerArea playerArea;


    public BangCharacterView(PlayerArea playerArea) {
        super(playerArea);
    }


    public PlayerArea getPlayerArea() {
        return playerArea;
    }

    /**
     * Pour afficher une carte masquée
     */
    public abstract void setVisible();

    /**
     * Pour masquer une carte affichée
     */
    public abstract void setUnVisible();

    /**
     * Pour définir l'action à exécuter quand la carte est sélectionnée par l'utilisateur
     */
    protected abstract void setBangCharacterSelectionListener();

}
