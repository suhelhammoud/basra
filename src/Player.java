import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Created by Suhel on 23/09/17.
 */
public class Player {

    public final String name;
    private boolean isTurn;

    private List<Card> currentCards;
    private List<Card> gainedCard;
    private List<Card> basraCards;

    public Player(String name) {
        this.name = name;
        this.isTurn = false;
        this.currentCards = new ArrayList<>();
        this.gainedCard = new ArrayList<>();
        this.basraCards = new ArrayList<>();
    }

    public int currentCardSize() {
        return currentCards.size();
    }

    public boolean addGainedCards(List<Card> cards) {
        return gainedCard.addAll(cards);
    }

    public void refill(List<Card> cards) {
        currentCards.addAll(cards);
    }

    public boolean throwCard(Card card) {
        //throws not found card exception
        return currentCards.remove(card);
    }

    public Card throwCard(int index) {
        //throws outOfIndex exception
        return currentCards.remove(index);
    }

    public static int calcPoints(List<Card> cards) {
        return cards.stream().mapToInt(Card::points).sum();
    }
    public int calcPoints(boolean gameIsEnded) {
        int result = basraCards.stream().mapToInt(Card::pasraValue).sum();
        result += calcPoints(gainedCard);
        result += gameIsEnded && gainedCard.size() > 26 ? 3 : 0;
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ")
                .add("name: " + name)
                .add("current:" + currentCards)
                .add("gained: " + gainedCard)
                .toString();
    }
}
