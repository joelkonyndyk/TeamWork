package package1;

import java.util.Random;

public class Deck {

	private Card[] deck;

	private final int deckSize = 52;
	private final int cardsInSuit = 13;

	// Constructor
	public Deck() {
		deck = new Card[deckSize];
		init();
	}

	// Initialize
	public void init() {
		Card tempCard;
		int suitCount = 1;
		int suitType = 1;

		for (int i = 0; i < deckSize; i++) {
			if (suitCount > 13) {
				suitCount = 1;
				suitType++;
			}
			deck[i] = new Card(suitCount, suitType, i + 1);
			suitCount++;
		}
	}

	// Methods
	public void outputDeck() {
		for (int i = 0; i < deckSize; i++) {
			System.out.println(deck[i].getCardPlace() + ": "
					+ deck[i].getCard() + " " + deck[i].getSuit());
		}
	}

	public void ShuffleDeck() {
		int index;
		Card temp;
		Random random = new Random();
		for (int i = deck.length - 1; i > 0; i--) {
			index = random.nextInt(i + 1);
			temp = deck[index];
			deck[index] = deck[i];
			deck[i] = temp;
		}
	}

	// Getters and Setters
}
