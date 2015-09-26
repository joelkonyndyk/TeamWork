package package1;

import java.util.Random;

public class Deck {

	private Card[] deck;

	private int[] suitType = { 3, 1, 2, 4 };

	private final int deckSize = 52;
	private final int cardsInSuit = 13;

	private SpriteSheet spriteSheet = new SpriteSheet();

	// Constructor
	public Deck() {
		deck = new Card[deckSize];

		spriteSheet.loadImage();
		spriteSheet.storeImages();

		init();
	}

	// Initialize
	public void init() {
		Card tempCard;
		int suitCount = 1;
		int sType = 0;

		Sprite tempSprite;

		for (int i = 0; i < deckSize; i++) {
			if (suitCount > 13) {
				suitCount = 1;
				sType++;
			}
			if (suitCount == 1) {
				suitCount = 14;
			}
			tempSprite = new Sprite(spriteSheet.getTile(i));
			deck[i] = new Card(suitCount, suitType[sType], i + 1, tempSprite);
			deck[i].setCardBack(spriteSheet.getTile(59));

			if (suitCount == 14) {
				suitCount = 2;
			} else {
				suitCount++;
			}
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

	// ///////////////////
	// Getters and Setters
	// ////////////////////

	public Card getCard(int i) {
		return deck[i];
	}

	public SpriteSheet getSpriteSheet() {
		return spriteSheet;
	}
}
