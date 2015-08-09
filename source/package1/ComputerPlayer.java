package package1;

public class ComputerPlayer {

	private String compName;
	private Deck deck;

	private Card[] hand;

	private boolean isTurn = false;

	private int deckSection;
	private int startCard;

	private final int handSize = 13;

	public ComputerPlayer(String name, Deck deck, int deckSection) {
		compName = name;
		this.deck = deck;
		this.deckSection = deckSection;
		init();
	}

	// Initialize
	public void init() {
		hand = new Card[handSize];

		if (deckSection == 1) {
			startCard = 1;
		} else if (deckSection == 2) {
			startCard = 14;
		} else if (deckSection == 3) {
			startCard = 27;
		}

		for (int i = 0; i < handSize; i++) {
			hand[i] = deck.getCard(startCard);
			startCard++;
		}
	}

}
