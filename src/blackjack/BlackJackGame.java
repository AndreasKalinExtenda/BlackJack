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
        // to add the dealer hand to numHands
        numHands = numHands+1;

        MainGame game = new MainGame(numHands, cards);
        game.startGame();
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
        System.out.print("7 top cards of the deck in order: ");
        for (int i = 0; i < 7; i++) {
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
    List<List<Card>> playerHands;
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

    public void hit(Card card) {
        this.cards.add(card);
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public void makeChoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 'h' to hit or 's' to stand:");
        String choice = scanner.nextLine();
        if (choice.equals("h")) {
            // Implement logic to hit here
            System.out.println("Player chose to hit");
        } else if (choice.equals("s")) {
            // Implement logic to stand here
            System.out.println("Player chose to stand");
        } else {
            System.out.println("Invalid choice. Please try again.");
            makeChoice();
        }
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
public class MainGame {
    private int numHands;
    int cardIndex = 0;
    private List<Card> cards;

    public MainGame(int numHands, List<Card> cards) {
        this.numHands = numHands;
        this.cards = cards;
    }

    public Card getCard() {
        Card card = this.cards.get(cardIndex);
        cardIndex++;
        return card;
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        Card card;
        List<List<Card>> playerHands = new ArrayList<>();
        for (int i = 0; i < numHands; i++) {
            playerHands.add(new ArrayList<>());
        }
        Dealer dealer = new Dealer("BOT Dealer");
        Player player = new Player("AndyPuff");

        Card[][] dealtCards = new Card[numHands][21];
        cardIndex = 0;

        // Two loops that deals the cards to number of combined hands for the dealer and the player
        for (int i = 0; i < numHands; i++) {
            card = getCard(); // method to get the next card
            dealer.dealCard(card);
            dealtCards[i][0] = card;
        }
        for (int i = 0; i < numHands; i++) {
            card = getCard(); // method to get the next card
            dealer.dealCard(card);
            dealtCards[i][1] = card;
        }

        int handNumber = 0;
        for (int i = 0; i < numHands; i++) {
            if (i == 0) {
                System.out.print("BOT Dealer has been dealt: " + "\n");
                Card firstCard = dealtCards[handNumber][0];
                System.out.println(firstCard.getDenomination() + " of " + firstCard.getSuit() + ", [Hidden card]");
            } else {
                System.out.print("AndyPuff has been dealt: " + "\n");
                Card firstCard = dealtCards[handNumber][0];
                Card secondCard = dealtCards[handNumber][1];
                List<Card> currentHand = Arrays.asList(firstCard, secondCard);

                int playerScore = BlackjackScoreCalculator.calculateScore(currentHand);
                if (playerScore == 21) {
                    System.out.println(firstCard.getDenomination() + " of " + firstCard.getSuit() + ", " + secondCard.getDenomination() + " of " + secondCard.getSuit() + "\t" + "\t" + "Blackjack for AndyPuff");
                } else {
                    boolean hasAce = false;
                    for (Card currentCard : currentHand) {
                        if (currentCard.getDenomination() == Denomination.ACE) {
                            hasAce = true;
                        }
                    }
                    if (hasAce) {
                        System.out.println(firstCard.getDenomination() + " of " + firstCard.getSuit() + ", " + secondCard.getDenomination() + " of " + secondCard.getSuit() + "\t" + "\t" + "AndyPuff has soft " + playerScore + " points");
                    } else {
                        System.out.println(firstCard.getDenomination() + " of " + firstCard.getSuit() + ", " + secondCard.getDenomination() + " of " + secondCard.getSuit() + "\t" + "\t" + "AndyPuff has " + playerScore + " points");
                    }
                }
            }
            handNumber++;
        }
        /*
        scanner = new Scanner(System.in);
        while (true) {
            System.out.println("AndyPuff, do you want to hit or stand? (h/s)");
            String choice = scanner.nextLine();
            if (choice.equals("h")) {
                for (int i = 1; i < numHands; i++) {
                    card = getCard(); // method to get the next card
                    dealer.dealCard(card);
                    dealtCards[i][2] = card;
                    //int playerScore = BlackjackScoreCalculator.calculateScore(currentHand);

                }
            } else if (choice.equals("s")) {
                break;
            }
        }*/

        /*
        for (int i = 0; i < numHands; i++) {
            List<Card> currentHand = new ArrayList<Card>();
            currentHand.add(dealtCards[i][0]);
            currentHand.add(dealtCards[i][1]);
            while (true) {
                System.out.println("AndyPuff, do you want to hit or stand? (h/s)");
                String choice = scanner.nextLine();
                if (choice.equals("h")) {
                    card = cards.get(cardIndex);
                    currentHand.add(card);
                    int playerScore = BlackjackScoreCalculator.calculateScore(currentHand);
                    System.out.println("AndyPuff has been dealt the " + card.getDenomination() + " of " + card.getSuit());
                    System.out.println("AndyPuff has " + playerScore + " points");
                    if (playerScore > 21) {
                        System.out.println("AndyPuff has busted.");
                        break;
                    }
                    else if(playerScore == 21)
                    {
                        break;
                    }
                    cardIndex++;
                }
                else if (choice.equals("s")) {
                    break;
                }
            }
        }*/
    }
}

