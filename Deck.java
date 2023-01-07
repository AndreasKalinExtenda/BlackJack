public class Deck {
    String suit;
    Int denomination;

    public String getSuit() {
        return suit;
    }

    public Int getDenomination() {
        return denomination;
    }

    public static Suit[] initializeSuits() {
        Suit[] suits = new Suit[4];
        suits[0] = new Suit("Hearts");
        suits[1] = new Suit("Diamonds");
        suits[2] = new Suit("Clubs");
        suits[3] = new Suit("Spades");
    }

    public Static Denomination[] initializeDenomination() {
        Denomination[] denominations = new Denomination[13];
        denominations[] = new Denomination("LowAce", 1);
        denominations[] = new Denomination("HighAce", 11);
        denominations[] = new Denomination("Two", 2);
        denominations[] = new Denomination("Three", 3);
        denominations[] = new Denomination("Four", 4);
        denominations[] = new Denomination("Five", 5);
        denominations[] = new Denomination("Six", 6);
        denominations[] = new Denomination("Seven", 7);
        denominations[] = new Denomination("Eight", 8);
        denominations[] = new Denomination("Nine", 9);
        denominations[] = new Denomination("Ten", 10);
        denominations[] = new Denomination("Jack", 10);
        denominations[] = new Denomination("Queen", 10);
        denominations[] = new Denomination("King", 10);
    }
}
