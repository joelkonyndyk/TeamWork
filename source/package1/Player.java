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
			// the -1 is added to account for the list starting at 0
			hand[i] = deck.getCard(startCard - 1);
			startCard++;
		}
	}
	
	// Getters and Setters
	public Card[] getHand(){
		return hand;
	}

	public boolean isTurn() {
		return isTurn;
	}

	public void setTurn(boolean isTurn) {
		this.isTurn = isTurn;
	}
}
