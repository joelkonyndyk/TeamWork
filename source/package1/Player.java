package package1;

public class Player {

	private String name;
	private Deck deck;

	private Card[] hand;

	private boolean isTurn = false;

	private int startCard;

	private final int handSize = 13;

	public Player(String name, Deck deck) {
		this.name = name;
		this.deck = deck;
		init();
	}

	// Initialize
	public void init() {
		hand = new Card[handSize];
		startCard = 40;

		for (int i = 0; i < handSize; i++) {
			hand[i] = deck.getCard(startCard);
			startCard++;
		}
	}
}
