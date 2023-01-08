//import java.util.ArrayList;
//import java.util.List;
import java.util.*;
public class BlackJackGame {
    public static void main(String[] args) {
        Deck deck = new Deck();
        List<Card> cards = deck.getCards();
        deck.printDeck();
    }
}

public enum Suit {
    CLUBS,
    DIAMONDS,
    HEARTS,
    SPADES;
}

public enum Denomination {
    ACE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    JACK,
    QUEEN,
    KING
}

public class Deck {
    public List<Card> cards;
    public Deck() {
        this.cards = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            // Creates four decks of cards
            List<Card> singleDeck = new ArrayList<>();
            for (Suit suit : Suit.values()) {
                for (Denomination denomination : Denomination.values()) {
                    Card card = new Card(suit, denomination);
                    singleDeck.add(card);
                }
            }

            // Add the cards from the single deck to the list of all cards
            this.cards.addAll(singleDeck);
        }
        for (int i = 0; i < 5; i++) {
            // Shuffle the 4 deck of cards 5 times
            Collections.shuffle(this.cards);
        }
    }
    public List<Card> getCards() {
        return this.cards;
    }
    public void printDeck() {
        // Print the shuffled list of cards
        for (Card card : this.cards) {
            System.out.println(card.getDenomination() + " of " + card.getSuit());
        }
        if (this.cards.size() != 208) {
            System.out.println("Error: Deck does not contain 208 cards!");
        }
    }
}


public class Card {

    private Suit suit;
    private Denomination denomination;

    public Card(Suit suit, Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    public Suit getSuit() {
        return this.suit;
    }

    public Denomination getDenomination() {
        return this.denomination;
    }
}


