package fr.umontpellier.iut.bang.logic.cards;

public class Winchester extends WeaponCard {
    public Winchester(int value, CardSuit suit) {
        super("Winchester", value, suit);
    }

    @Override
    public int getRange() {
        return 5;
    }
}
