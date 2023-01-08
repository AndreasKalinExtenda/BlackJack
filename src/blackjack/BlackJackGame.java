import java.util.*;

public class BlackJackGame {
    public static void main(String[] args) {
        Card card;
        Deck deck = new Deck();
        List<Card> cards = deck.getCards();
        deck.printDeck();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of hands to play (1-7):");
        int numHands = scanner.nextInt();
        while (numHands < 1 || numHands > 7) {
            System.out.println("Invalid number of hands. Enter a number between 1 and 7:");
            numHands = scanner.nextInt();
        }

        Dealer dealer = new Dealer("BOT Dealer");
        Player player = new Player("AndyPuff");

        for (int i = 0; i < 2; i++) {
            card = cards.get(i * (numHands + 1));
            dealer.dealCard(card);
            for (int j = 0; j < numHands; j++) {
                card = cards.get(i * (numHands + 1) + j + 1);
                player.dealCard(card);
            }
        }

        /* Hide this print out as its hiding the second card of the dealer. Right now i want it to be shown for debug reasons
        System.out.println("BOT Dealer has been dealt:");
        Card firstCard = dealer.getCards().get(0);
        int dealerScore = BlackjackScoreCalculator.calculateScore(dealer.getCards());
        System.out.println(firstCard.getDenomination() + " of " + firstCard.getSuit() + "\t" + "\t" + "BOT Dealer has " + dealerScore + " points");
        System.out.println("[Hidden Card]"); */

        System.out.print("BOT Dealer has been dealt: " + "\n");
        Card firstCard = dealer.getCards().get(0);
        Card secondCard = dealer.getCards().get(1);
        int dealerScore = BlackjackScoreCalculator.calculateScore(dealer.getCards());
        System.out.println(firstCard.getDenomination() + " of " + firstCard.getSuit() + ", " + secondCard.getDenomination() + " of " + secondCard.getSuit() + "\t" + "\t" + "BOT Dealer has " + dealerScore + " points");

        if (player.getCards().size() < numHands * 2) {
            System.out.println("Error: Not enough cards to play " + numHands + " hands!");
            return;
        }

        System.out.println("AndyPuff has been dealt " + numHands + " hands:");
        for (int i = 0; i < numHands * 2; i += 2) {
            Card card1 = player.getCards().get(i);
            Card card2 = player.getCards().get(i + 1);
            int playerScore = BlackjackScoreCalculator.calculateScore(player.getCards());
            System.out.println(card1.getDenomination() + " of " + card1.getSuit() + ", " + card2.getDenomination() + " of " + card2.getSuit() + "\t" + "\t" + "AndyPuff has " + playerScore + " points");
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
        //for (Card card : this.cards) {
        //    System.out.println(card.getDenomination() + " of " + card.getSuit());
        //}
        System.out.print("20 top cards of the deck in order: ");
        for (int i = 0; i < 20; i++) {
            Card card = cards.get(i);
            System.out.print(card.getDenomination() + " of " + card.getSuit());
            if (i < 19) {
                System.out.print(", ");
            }
        }
        System.out.println();
        if (this.cards.size() != 208) {
            System.out.println("Error: Deck does not contain 208 cards!");
        }
    }
}
public enum Denomination {
    ACE(11),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    QUEEN(10),
    KING(10);

    private int value;

    Denomination(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

public enum Suit {
    CLUBS,
    DIAMONDS,
    HEARTS,
    SPADES;
}
public class Dealer {
    private String name;
    private List<Card> cards;

    public Dealer(String name) {
        this.name = name;
        this.cards = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public void dealCard(Card card) {
        this.cards.add(card);
    }
}
public class Player {
    private String name;
    private List<Card> cards;

    public Player(String name) {
        this.name = name;
        this.cards = new ArrayList<>();
    }

    public void dealCard(Card card) {
        this.cards.add(card);
    }

    public List<Card> getCards() {
        return this.cards;
    }
}
public class BlackjackScoreCalculator {
    public static int calculateScore(List<Card> hand) {
        int score = 0;
        int numAces = 0;
        for (Card card : hand) {
            Denomination denomination = card.getDenomination();
            switch (denomination) {
                case ACE:
                    score += 11;
                    numAces++;
                    break;
                case TWO:
                    score += 2;
                    break;
                case THREE:
                    score += 3;
                    break;
                case FOUR:
                    score += 4;
                    break;
                case FIVE:
                    score += 5;
                    break;
                case SIX:
                    score += 6;
                    break;
                case SEVEN:
                    score += 7;
                    break;
                case EIGHT:
                    score += 8;
                    break;
                case NINE:
                    score += 9;
                    break;
                case TEN:
                case JACK:
                case QUEEN:
                case KING:
                    score += 10;
                    break;
            }
        }
        // If the score is greater than 21 and there is at least one ace, try changing all the aces to have a value of 1 instead of 11.
        while (score > 21 && numAces > 0) {
            score -= 10;
            numAces--;
        }
        return score;
    }
}


