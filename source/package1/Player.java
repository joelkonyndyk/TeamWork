package package1;

public class Player {

	private String name;
	private Deck deck;

	private Card[] hand;
	private Card[] sort;

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
		sort = new Card[handSize];
		startCard = 40;

		for (int i = 0; i < handSize; i++) {
			// the -1 is added to account for the list starting at 0
			hand[i] = deck.getCard(startCard - 1);
			startCard++;
		}
	}

	public void sortHand() {

		boolean swapped = true;
		int j = 0;
		Card tmp;
		while (swapped) {
			swapped = false;
			j++;
			for (int i = 0; i < hand.length - j; i++) {
				if (hand[i].getSuitNumber() > hand[i + 1].getSuitNumber()) {
					tmp = hand[i];
					hand[i] = hand[i + 1];
					hand[i + 1] = tmp;
					swapped = true;
				}
				if (hand[i].getSuitNumber() == hand[i + 1].getSuitNumber()) {
					if (hand[i].getCardNumber() > hand[i + 1].getCardNumber()) {
						tmp = hand[i];
						hand[i] = hand[i + 1];
						hand[i + 1] = tmp;
						swapped = true;
					}
				}
			}
		}
	}

	// ///////////////////
	// Getters and Setters
	// ////////////////////

	public String getName() {
		return name;
	}

	public Card[] getHand() {
		return hand;
	}

	public boolean isTurn() {
		return isTurn;
	}

	public void setTurn(boolean isTurn) {
		this.isTurn = isTurn;
	}
}
