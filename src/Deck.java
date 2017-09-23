import java.util.*;

/**
 * Created by Suhel on 23/09/17.
 */
public class Deck {
    private LinkedList<Card> cards = new LinkedList<>();

    public static Deck get(boolean shuffle) {
        Deck result = new Deck();
        for (SUIT suit : SUIT.values())
            for (RANK rank : RANK.values())
                result.cards.add(new Card(suit, rank));

        if (shuffle)
            Collections.shuffle(result.cards);
        return result;
    }

    public int size() {
        return cards.size();
    }

    public List<Card> getAndRemoveFromDeck(int num) {
        //if size < num throws exception
        List<Card> result = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            result.add(cards.removeFirst());
        }
        return result;
    }

    @Override
    public String toString() {
        return cards.toString();
    }

    public static void main(String[] args) {
        Deck deck = Deck.get(true);
        System.out.println(deck);
    }
}
