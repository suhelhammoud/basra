import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Suhel on 23/09/17.
 */
public class Game {
    final Player p1, p2;
    Player player;
    List<Card> ground;
    Deck deck;

    String lastGained = "";

    public Game(String p1Name, String p2Name) {
        this.p1 = new Player(p1Name);
        this.p2 = new Player(p2Name);
        this.player = p1;
        init();
    }

    public boolean needRefill() {
        return p1.currentCardSize() == 0
                && p2.currentCardSize() == 0;
    }

    public void refillPlayers() {
        p1.refill(deck.getAndRemoveFromDeck(6));
        p2.refill(deck.getAndRemoveFromDeck(6));

    }

    private void init() {
        deck = Deck.get(true);
        ground.addAll(deck.getAndRemoveFromDeck(6));
        refillPlayers();
    }

    public boolean gameIsEnded() {
        return deck.size() == 0
                && p1.currentCardSize() == 0
                && p2.currentCardSize() == 0;
    }

    public String printStatus(boolean debug) {
        StringJoiner result = new StringJoiner("\n");
        if (debug) result.add("deck: " + deck.toString());

        result.add("player 1: " + p1.toString());
        result.add("player 2: " + p2.toString());
        result.add("ground: " + ground.toString());

        return result.toString();
    }


    public Player switchUsers() {
        player = player == p1 ? p2 : p1;
        return player;
    }

    public static int sum(List<Card> cards) {
        return cards.stream().mapToInt(Card::value).sum();
    }

    public static List<Card> take(Card card, List<Card> ground) {

        if (ground.size() == 0) return new ArrayList<>();

        switch (card.rank) {
            case JACK:
                return new ArrayList<>(ground);
            case QUEEN:
                return ground.stream()
                        .filter(c -> c.rank == RANK.QUEEN)
                        .collect(Collectors.toList());
            case KING:
                return ground.stream()
                        .filter(c -> c.rank == RANK.KING)
                        .collect(Collectors.toList());
            default:
                List<Card> tGround = ground.stream()
                        .filter(c -> c.rank != RANK.QUEEN
                                || c.rank != RANK.KING
                                || c.rank != RANK.JACK)
                        .collect(Collectors.toList());
                return take2(card, tGround);
        }

    }

    public static List<Card> take2(Card card, List<Card> ground) {
        int cardValue = card.value();
        int maxSize = 0;
        List<Card> result = new ArrayList<>();

        List<List<Card>> possibleSets = new ArrayList<>();
        for (PowerSetIterator it = new PowerSetIterator(ground); it.hasNext(); ) {
            List<Card> cards = it.next();
            if (cardValue == sum(cards) && cards.size() > maxSize) {
                maxSize = cards.size();
                result = cards;
            }
        }
        return result;
    }

    public static void run(String p1Name, String p2Name) {
        System.out.println("Starting Game");
        Game game = new Game("Maryam", "Laila");

        while (!game.gameIsEnded()) {
            System.out.println(game.printStatus(false));

        }
    }

}
