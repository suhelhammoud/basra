/**
 * Created by Suhel on 23/09/17.
 *
 */
public class Card {
    public final SUIT suit;
    public final RANK rank;

    public Card(SUIT suit, RANK rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public int value() {
        return rank.ordinal() < 10 ? rank.ordinal() + 1 : 0;
    }

    public int pasraValue() {
        switch (rank) {
            case JACK:
                return 0;
            case QUEEN:
            case KING:
                return 5;
            default:
                return rank.ordinal() + 1;
        }
    }

    public int points() {
        switch (rank) {
            case R1:    //Ace
            case JACK:  //Jack
                return 1;
            case R10:   // 10 Diamond
                return suit == SUIT.DIAMOND ? 3 : 0;
            case R2:    //
                return suit == SUIT.CLUBS ? 2 : 0;
            default:
                return 0;
        }
    }

    @Override
    public String toString() {
        return suit.toSymbol() + rank.toString();
    }

    public static void main(String[] args) {
        //Test
        System.out.println(new Card(SUIT.DIAMOND, RANK.QUEEN));
    }
}
