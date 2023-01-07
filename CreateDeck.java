import java.util.Random;
public class CreateDeck {
    //Suit suit;
    //Denomination denomination;

    //public Cards (Suit suit, Denomination denomination) {
    //    this.suit = suit;
    //    this.denomination = denomination;
    Card[] cards;

    public deck() {
        cards = new Card[52];
        int index = 0;
        for (Suit suit : Suit.values()) {
            for (Denomination denomination : Denomination.values()) {
                cards[index] = new Card (denomination, suit);
            }
        }
    }
}
