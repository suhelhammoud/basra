/**
 * Created by Suhel on 23/09/17.
 */

public enum SUIT {

    SPADE, HEARTS, DIAMOND, CLUBS;

    /* ["\u2660", "\u2661", "\u2662", "\u2663"] */

    public String toSymbol() {
        return new String[]{"♠", "♡", "♢", "♣"}[ordinal()];
    }

}

